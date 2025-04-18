/*
 * Copyright 2018 OpenAPI-Generator Contributors (https://openapi-generator.tech)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openapitools.codegen.languages;

import com.google.common.collect.ImmutableMap;
import com.samskivert.mustache.Mustache.Lambda;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.codegen.*;
import org.openapitools.codegen.model.ModelMap;
import org.openapitools.codegen.model.ModelsMap;
import org.openapitools.codegen.model.OperationMap;
import org.openapitools.codegen.model.OperationsMap;
import org.openapitools.codegen.templating.mustache.CamelCaseAndSanitizeLambda;
import org.openapitools.codegen.utils.ModelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

import static org.openapitools.codegen.utils.CamelizeOption.LOWERCASE_FIRST_LETTER;
import static org.openapitools.codegen.utils.StringUtils.camelize;
import static org.openapitools.codegen.utils.StringUtils.underscore;

public abstract class AbstractFSharpCodegen extends DefaultCodegen implements CodegenConfig {

    protected boolean optionalAssemblyInfoFlag = true;
    protected boolean optionalProjectFileFlag = true;

    protected boolean useDateTimeOffsetFlag = false;
    protected boolean useCollection = false;
    @Setter protected boolean returnICollection = false;
    @Setter protected boolean netCoreProjectFileFlag = false;

    @Getter protected String modelPropertyNaming = CodegenConstants.MODEL_PROPERTY_NAMING_TYPE.PascalCase.name();

    @Setter protected String licenseUrl = "http://localhost";
    @Setter protected String licenseName = "NoLicense";

    @Setter protected String packageVersion = "1.0.0";
    protected String packageName = "OpenAPI";
    @Setter protected String packageTitle = "OpenAPI Library";
    @Setter protected String packageProductName = "OpenAPILibrary";
    @Setter protected String packageDescription = "A library generated from a OpenAPI doc";
    @Setter protected String packageCompany = "OpenAPI";
    @Setter protected String packageCopyright = "No Copyright";
    @Setter protected String packageAuthors = "OpenAPI";

    @Getter @Setter
    protected String interfacePrefix = "I";

    protected String projectFolder = packageName;
    @Setter protected String sourceFolder = projectFolder + File.separator + "src";
    protected String testFolder = projectFolder + ".Tests";

    protected Set<String> collectionTypes;
    protected Set<String> mapTypes;

    // true if nullable types will be supported (as option)
    @Getter @Setter
    protected boolean supportNullable = Boolean.TRUE;

    protected Set<String> nullableType = new HashSet<>();


    private final Logger LOGGER = LoggerFactory.getLogger(AbstractFSharpCodegen.class);

    public AbstractFSharpCodegen() {
        super();

        supportsInheritance = true;

        importMapping.clear();
        importMapping.put("IDictionary", "System.Collections.Generic");

        outputFolder = this.getName();
        embeddedTemplateDir = templateDir = this.getName();

        collectionTypes = new HashSet<>(Arrays.asList("list", "seq"));

        mapTypes = new HashSet<>(
                Arrays.asList("IDictionary")
        );

        reservedWords.addAll(
                Arrays.asList(
                        // local variable names in API methods (endpoints)
                        "localVarPath", "localVarPathParams", "localVarQueryParams", "localVarHeaderParams",
                        "localVarFormParams", "localVarFileParams", "localVarStatusCode", "localVarResponse",
                        "localVarPostBody", "localVarHttpHeaderAccepts", "localVarHttpHeaderAccept",
                        "localVarHttpContentTypes", "localVarHttpContentType",
                        "localVarStatusCode",
                        // F# reserved words
                        "abstract", "and", "as", "async", "await", "assert", "base", "begin", "bool", "break", "byte", "case", "catch", "char", "checked",
                        "class", "const", "continue", "decimal", "default", "delegate", "do", "done", "double", "downcast", "downto", "dynamic",
                        "elif", "else", "end", "enum", "event", "exception", "explicit", "extern", "false", "finally", "fixed", "float", "for",
                        "foreach", "fun", "function", "if", "in", "inherit", "inline", "int", "interface", "internal", "is", "lazy", "let", "let!", "lock",
                        "match", "match!", "member", "module", "mutable", "namespace", "new", "not", "null", "of", "open", "option", "or", "override", "params",
                        "private", "public", "raise", "rec", "return", "return!", "sealed", "select", "static", "string", "struct", "then", "to",
                        "true", "try", "type", "upcast", "use", "use!", "val", "void", "volatile", "when", "while", "with", "yield", "yield!")
        );

        // TODO - these are based on C# generator, do we need to add any more?
        languageSpecificPrimitives = new HashSet<>(
                Arrays.asList(
                        "String",
                        "string",
                        "bool",
                        "char",
                        "decimal",
                        "int",
                        "int16",
                        "int64",
                        "nativeint",
                        "unativeint",
                        "uint16",
                        "uint32",
                        "uint64",
                        "float",
                        "byte[]",
                        "ICollection",
                        "Collection",
                        "list",
                        "dict",
                        "seq",
                        "Dictionary",
                        "List",
                        "DateTime",
                        "DataTimeOffset",
                        "Double",
                        "Int32",
                        "Int64",
                        "float",
                        "float32",
                        "single",
                        "double",
                        "System.IO.Stream", // not really a primitive, we include it to avoid model import
                        "obj")
        );

        instantiationTypes.put("array", "list");
        instantiationTypes.put("list", "list");
        instantiationTypes.put("map", "IDictionary");


        typeMapping = new HashMap<>();
        typeMapping.put("string", "string");
        typeMapping.put("binary", "byte[]");
        typeMapping.put("ByteArray", "byte[]");
        typeMapping.put("boolean", "bool");
        typeMapping.put("integer", "int");
        typeMapping.put("float", "float");
        typeMapping.put("long", "int64");
        typeMapping.put("double", "double");
        typeMapping.put("number", "decimal");
        typeMapping.put("DateTime", "DateTime");
        typeMapping.put("date", "DateTime");
        typeMapping.put("file", "System.IO.Stream");
        typeMapping.put("array", "list");
        typeMapping.put("list", "list");
        typeMapping.put("map", "IDictionary");
        typeMapping.put("object", "obj");
        typeMapping.put("UUID", "Guid");
        typeMapping.put("URI", "string");

        // nullable type
        nullableType = new HashSet<>(
                Arrays.asList("decimal", "bool", "int", "float", "long", "double", "string", "Guid", "apiKey")
        );
    }

    public void setUseCollection(boolean useCollection) {
        this.useCollection = useCollection;
        if (useCollection) {
            typeMapping.put("array", "seq");
            typeMapping.put("list", "seq");

            instantiationTypes.put("array", "seq");
            instantiationTypes.put("list", "seq");
        }
    }

    public void useDateTimeOffset(boolean flag) {
        this.useDateTimeOffsetFlag = flag;
        if (flag) {
            typeMapping.put("DateTime", "DateTimeOffset?");
        } else {
            typeMapping.put("DateTime", "DateTime?");
        }
    }

    @Override
    public void processOpts() {
        super.processOpts();

        // License info
        if (additionalProperties.containsKey(CodegenConstants.LICENSE_URL)) {
            setLicenseUrl((String) additionalProperties.get(CodegenConstants.LICENSE_URL));
        } else {
            additionalProperties.put(CodegenConstants.LICENSE_URL, this.licenseUrl);
        }

        if (additionalProperties.containsKey(CodegenConstants.LICENSE_NAME)) {
            setLicenseName((String) additionalProperties.get(CodegenConstants.LICENSE_NAME));
        } else {
            additionalProperties.put(CodegenConstants.LICENSE_NAME, this.licenseName);
        }

        // {{packageVersion}}
        if (additionalProperties.containsKey(CodegenConstants.PACKAGE_VERSION)) {
            setPackageVersion((String) additionalProperties.get(CodegenConstants.PACKAGE_VERSION));
        } else {
            additionalProperties.put(CodegenConstants.PACKAGE_VERSION, packageVersion);
        }

        // {{sourceFolder}}
        if (additionalProperties.containsKey(CodegenConstants.SOURCE_FOLDER)) {
            setSourceFolder((String) additionalProperties.get(CodegenConstants.SOURCE_FOLDER));
        } else {
            additionalProperties.put(CodegenConstants.SOURCE_FOLDER, this.sourceFolder);
        }

        // {{packageName}}
        if (additionalProperties.containsKey(CodegenConstants.PACKAGE_NAME)) {
            setPackageName((String) additionalProperties.get(CodegenConstants.PACKAGE_NAME));
        } else {
            additionalProperties.put(CodegenConstants.PACKAGE_NAME, packageName);
        }

        // {{packageTitle}}
        if (additionalProperties.containsKey(CodegenConstants.PACKAGE_TITLE)) {
            setPackageTitle((String) additionalProperties.get(CodegenConstants.PACKAGE_TITLE));
        } else {
            additionalProperties.put(CodegenConstants.PACKAGE_TITLE, packageTitle);
        }

        // {{packageProductName}}
        if (additionalProperties.containsKey(CodegenConstants.PACKAGE_PRODUCTNAME)) {
            setPackageProductName((String) additionalProperties.get(CodegenConstants.PACKAGE_PRODUCTNAME));
        } else {
            additionalProperties.put(CodegenConstants.PACKAGE_PRODUCTNAME, packageProductName);
        }

        // {{packageDescription}}
        if (additionalProperties.containsKey(CodegenConstants.PACKAGE_DESCRIPTION)) {
            setPackageDescription((String) additionalProperties.get(CodegenConstants.PACKAGE_DESCRIPTION));
        } else {
            additionalProperties.put(CodegenConstants.PACKAGE_DESCRIPTION, packageDescription);
        }

        // {{packageCompany}}
        if (additionalProperties.containsKey(CodegenConstants.PACKAGE_COMPANY)) {
            setPackageCompany((String) additionalProperties.get(CodegenConstants.PACKAGE_COMPANY));
        } else {
            additionalProperties.put(CodegenConstants.PACKAGE_COMPANY, packageCompany);
        }

        // {{packageCopyright}}
        if (additionalProperties.containsKey(CodegenConstants.PACKAGE_COPYRIGHT)) {
            setPackageCopyright((String) additionalProperties.get(CodegenConstants.PACKAGE_COPYRIGHT));
        } else {
            additionalProperties.put(CodegenConstants.PACKAGE_COPYRIGHT, packageCopyright);
        }

        // {{packageAuthors}}
        if (additionalProperties.containsKey(CodegenConstants.PACKAGE_AUTHORS)) {
            setPackageAuthors((String) additionalProperties.get(CodegenConstants.PACKAGE_AUTHORS));
        } else {
            additionalProperties.put(CodegenConstants.PACKAGE_AUTHORS, packageAuthors);
        }

        // {{useDateTimeOffset}}
        if (additionalProperties.containsKey(CodegenConstants.USE_DATETIME_OFFSET)) {
            useDateTimeOffset(convertPropertyToBooleanAndWriteBack(CodegenConstants.USE_DATETIME_OFFSET));
        } else {
            additionalProperties.put(CodegenConstants.USE_DATETIME_OFFSET, useDateTimeOffsetFlag);
        }

        if (additionalProperties.containsKey(CodegenConstants.MODEL_PROPERTY_NAMING)) {
            setModelPropertyNaming((String) additionalProperties.get(CodegenConstants.MODEL_PROPERTY_NAMING));
        }

        // This either updates additionalProperties with the above fixes, or sets the default if the option was not specified.
        additionalProperties.put(CodegenConstants.INTERFACE_PREFIX, interfacePrefix);
    }

    @Override
    protected ImmutableMap.Builder<String, Lambda> addMustacheLambdas() {
        return super.addMustacheLambdas()
                .put("camelcase_param", new CamelCaseAndSanitizeLambda().generator(this).escapeAsParamName(true));
    }

    @Override
    public ModelsMap postProcessModels(ModelsMap objs) {
        super.postProcessModels(objs);
        for (ModelMap mo : objs.getModels()) {
            CodegenModel cm = mo.getModel();
            for (CodegenProperty var : cm.vars) {
                // check to see if model name is same as the property name
                // which will result in compilation error
                // if found, prepend with _ to workaround the limitation
                if (var.name.equalsIgnoreCase(cm.name)) {
                    var.name = "_" + var.name;
                }
            }
        }
        // process enum in models
        return postProcessModelsEnum(objs);
    }

    /**
     * Invoked by {@link DefaultGenerator} after all models have been post-processed, allowing for a last pass of codegen-specific model cleanup.
     *
     * @param objs Current state of codegen object model.
     * @return (ew) modified state of the codegen object model.
     */
    @Override
    public Map<String, ModelsMap> postProcessAllModels(Map<String, ModelsMap> objs) {
        final Map<String, ModelsMap> processed = super.postProcessAllModels(objs);
        postProcessEnumRefs(processed);
        return postProcessDependencyOrders(processed);
    }

    /*
     * F# does not allow forward declarations, so files must be imported in the correct order.
     * Output of CodeGen models must therefore be in dependency order (rather than alphabetical order, which seems to be the default).
     * This could probably be made more efficient if absolutely needed.
     */
    public Map<String, ModelsMap> postProcessDependencyOrders(final Map<String, ModelsMap> objs) {

        Map<String, Set<String>> dependencies = new HashMap<>();

        List<String> classNames = new ArrayList<>();

        for (String k : objs.keySet()) {
            CodegenModel model = ModelUtils.getModelByName(k, objs);
            if (model == null || model.classname == null) {
                throw new RuntimeException("Null model encountered");
            }
            dependencies.put(model.classname, model.imports);

            classNames.add(model.classname);
        }

        Object[] sortedKeys = classNames.toArray();

        for (int i1 = 0; i1 < sortedKeys.length; i1++) {
            String k1 = sortedKeys[i1].toString();
            for (int i2 = i1 + 1; i2 < sortedKeys.length; i2++) {
                String k2 = sortedKeys[i2].toString();
                if (dependencies.get(k2).contains(k1)) {
                    sortedKeys[i2] = k1;
                    sortedKeys[i1] = k2;
                    i1 = -1;
                    break;
                }
            }
        }

        Map<String, ModelsMap> sorted = new LinkedHashMap<>();
        for (int i = sortedKeys.length - 1; i >= 0; i--) {
            Object k = sortedKeys[i];
            sorted.put(k.toString(), objs.get(k));
        }

        return sorted;
    }

    /**
     * F# differs from other languages in that Enums are not _true_ objects; enums are compiled to integral types.
     * So, in F#, an enum is considers more like a user-defined primitive.
     * <p>
     * When working with enums, we can't always assume a RefModel is a nullable type (where default(YourType) == null),
     * so this post processing runs through all models to find RefModel'd enums. Then, it runs through all vars and modifies
     * those vars referencing RefModel'd enums to work the same as inlined enums rather than as objects.
     *
     * @param models processed models to be further processed for enum references
     */
    @SuppressWarnings("unchecked")
    private void postProcessEnumRefs(final Map<String, ModelsMap> models) {
        Map<String, CodegenModel> enumRefs = new HashMap<>();
        for (String key : models.keySet()) {
            CodegenModel model = ModelUtils.getModelByName(key, models);
            if (model.isEnum) {
                enumRefs.put(key, model);
            }
        }

        for (String openAPIName : models.keySet()) {
            CodegenModel model = ModelUtils.getModelByName(openAPIName, models);
            if (model != null) {
                for (CodegenProperty var : model.allVars) {
                    if (enumRefs.containsKey(var.dataType)) {
                        // Handle any enum properties referred to by $ref.
                        // This is different in F# than most other generators, because enums in C# are compiled to integral types,
                        // while enums in many other languages are true objects.
                        CodegenModel refModel = enumRefs.get(var.dataType);
                        var.allowableValues = refModel.allowableValues;
                        var.isEnum = true;

                        // We do these after updateCodegenPropertyEnum to avoid generalities that don't mesh with C#.
                        var.isPrimitiveType = true;
                    }
                }

                // We're looping all models here.
                if (model.isEnum) {
                    // We now need to make allowableValues.enumVars look like the context of CodegenProperty
                    Boolean isString = false;
                    Boolean isInteger = false;
                    Boolean isLong = false;
                    Boolean isByte = false;

                    if (model.dataType.startsWith("byte")) {
                        // F# Actually supports byte and short enums, swagger spec only supports byte.
                        isByte = true;
                        model.vendorExtensions.put("x-enum-byte", true);
                    } else if (model.dataType.startsWith("int32")) {
                        isInteger = true;
                        model.vendorExtensions.put("x-enum-integer", true);
                    } else if (model.dataType.startsWith("int64")) {
                        isLong = true;
                        model.vendorExtensions.put("x-enum-long", true);
                    } else {
                        // F# doesn't support non-integral enums, so we need to treat everything else as strings (e.g. to not lose precision or data integrity)
                        isString = true;
                        model.vendorExtensions.put("x-enum-string", true);
                    }

                    // Since we iterate enumVars for modelInnerEnum and enumClass templates, and CodegenModel is missing some of CodegenProperty's properties,
                    // we can take advantage of Mustache's contextual lookup to add the same "properties" to the model's enumVars scope rather than CodegenProperty's scope.
                    List<Map<String, String>> enumVars = (ArrayList<Map<String, String>>) model.allowableValues.get("enumVars");
                    List<Map<String, Object>> newEnumVars = new ArrayList<>();
                    for (Map<String, String> enumVar : enumVars) {
                        Map<String, Object> mixedVars = new HashMap<>(enumVar);

                        mixedVars.put("isString", isString);
                        mixedVars.put("isLong", isLong);
                        mixedVars.put("isInteger", isInteger);
                        mixedVars.put("isByte", isByte);

                        newEnumVars.add(mixedVars);
                    }

                    if (!newEnumVars.isEmpty()) {
                        model.allowableValues.put("enumVars", newEnumVars);
                    }
                }
            } else {
                LOGGER.warn("Expected to retrieve model {} by name, but no model was found. Check your -Dmodels inclusions.", openAPIName);
            }
        }
    }

    /**
     * Update codegen property's enum by adding "enumVars" (with name and value)
     *
     * @param var list of CodegenProperty
     */
    @Override
    public void updateCodegenPropertyEnum(CodegenProperty var) {
        if (var.vendorExtensions == null) {
            var.vendorExtensions = new HashMap<>();
        }

        super.updateCodegenPropertyEnum(var);

        // Because C# uses nullable primitives for datatype, and datatype is used in DefaultCodegen for determining enum-ness, guard against weirdness here.
        if (var.isEnum) {
            if ("byte".equals(var.dataFormat)) {// C# Actually supports byte and short enums.
                var.vendorExtensions.put("x-enum-byte", true);
                var.isString = false;
                var.isLong = false;
                var.isInteger = false;
            } else if ("int32".equals(var.dataFormat)) {
                var.isInteger = true;
                var.isString = false;
                var.isLong = false;
            } else if ("int64".equals(var.dataFormat)) {
                var.isLong = true;
                var.isString = false;
                var.isInteger = false;
            } else {// C# doesn't support non-integral enums, so we need to treat everything else as strings (e.g. to not lose precision or data integrity)
                var.isString = true;
                var.isInteger = false;
                var.isLong = false;
            }
        }
    }

    @Override
    public OperationsMap postProcessOperationsWithModels(OperationsMap objs, List<ModelMap> allModels) {
        super.postProcessOperationsWithModels(objs, allModels);
        if (objs != null) {
            OperationMap operations = objs.getOperations();
            if (operations != null) {
                List<CodegenOperation> ops = operations.getOperation();
                for (CodegenOperation operation : ops) {

                    // Check return types for collection
                    if (operation.returnType != null) {
                        String typeMapping;
                        int namespaceEnd = operation.returnType.lastIndexOf(".");
                        if (namespaceEnd > 0) {
                            typeMapping = operation.returnType.substring(namespaceEnd);
                        } else {
                            typeMapping = operation.returnType;
                        }

                        if (this.collectionTypes.contains(typeMapping)) {
                            operation.isArray = true;
                            operation.returnContainer = operation.returnType;
                            if (this.returnICollection && (
                                    typeMapping.startsWith("List") ||
                                            typeMapping.startsWith("Collection"))) {
                                // NOTE: ICollection works for both List<T> and Collection<T>
                                int genericStart = typeMapping.indexOf("<");
                                if (genericStart > 0) {
                                    operation.returnType = "ICollection" + typeMapping.substring(genericStart);
                                }
                            }
                        } else {
                            operation.returnContainer = operation.returnType;
                            operation.isMap = this.mapTypes.contains(typeMapping);
                        }
                    }

                    if (operation.examples != null) {
                        for (Map<String, String> example : operation.examples) {
                            for (Map.Entry<String, String> entry : example.entrySet()) {
                                // Replace " with \", \r, \n with \\r, \\n
                                String val = entry.getValue().replace("\"", "\\\"")
                                        .replace("\r", "\\r")
                                        .replace("\n", "\\n");
                                entry.setValue(val);
                            }
                        }
                    }

                    if (!isSupportNullable()) {
                        for (CodegenParameter parameter : operation.allParams) {
                            CodegenModel model = null;
                            for (ModelMap modelHashMap : allModels) {
                                CodegenModel codegenModel = modelHashMap.getModel();
                                if (codegenModel.getClassname().equals(parameter.dataType)) {
                                    model = codegenModel;
                                    break;
                                }
                            }

                            if (model == null) {
                                // Primitive data types all come already marked
                                parameter.isNullable = true;
                            } else {
                                // Effectively mark enum models as enums and non-nullable
                                if (model.isEnum) {
                                    parameter.isEnum = true;
                                    parameter.allowableValues = model.allowableValues;
                                    parameter.isPrimitiveType = true;
                                    parameter.isNullable = false;
                                } else {
                                    parameter.isNullable = true;
                                }
                            }
                        }
                    }

                    processOperation(operation);
                }
            }
        }

        return objs;
    }

    protected void processOperation(CodegenOperation operation) {
        // default noop
    }

    @Override
    public String apiFileFolder() {
        return outputFolder + File.separator + sourceFolder + File.separator + apiPackage();
    }

    @Override
    public String modelFileFolder() {
        return outputFolder + File.separator + sourceFolder + File.separator + modelPackage();
    }

    @Override
    public String toModelFilename(String name) {
        // should be the same as the model name
        return toModelName(name);
    }

    @Override
    public String toOperationId(String operationId) {
        // throw exception if method name is empty (should not occur as an auto-generated method name will be used)
        if (StringUtils.isEmpty(operationId)) {
            throw new RuntimeException("Empty method name (operationId) not allowed");
        }

        // method name cannot use reserved keyword, e.g. return
        if (isReservedWord(operationId)) {
            LOGGER.warn("{} (reserved word) cannot be used as method name. Renamed to {}", operationId, camelize(sanitizeName("call_" + operationId)));
            operationId = "call_" + operationId;
        }

        // operationId starts with a number
        if (operationId.matches("^\\d.*")) {
            LOGGER.warn("{} (starting with a number) cannot be used as method name. Renamed to {}", operationId, camelize(sanitizeName("call_" + operationId)));
            operationId = "call_" + operationId;
        }

        return camelize(sanitizeName(operationId));
    }

    public void setModelPropertyNaming(String naming) {
        if ("original".equals(naming) || "camelCase".equals(naming) ||
                "PascalCase".equals(naming) || "snake_case".equals(naming)) {
            this.modelPropertyNaming = naming;
        } else {
            throw new IllegalArgumentException("Invalid model property naming '" +
                    naming + "'. Must be 'original', 'camelCase', " +
                    "'PascalCase' or 'snake_case'");
        }
    }


    public String getNameUsingModelPropertyNaming(String name) {
        switch (CodegenConstants.MODEL_PROPERTY_NAMING_TYPE.valueOf(getModelPropertyNaming())) {
            case original:
                return name;
            case camelCase:
                return camelize(name, LOWERCASE_FIRST_LETTER);
            case PascalCase:
                return camelize(name);
            case snake_case:
                return underscore(name);
            default:
                throw new IllegalArgumentException("Invalid model property naming '" +
                        name + "'. Must be 'original', 'camelCase', " +
                        "'PascalCase' or 'snake_case'");
        }
    }

    @Override
    public String toVarName(String name) {
        // sanitize name
        name = sanitizeName(name);

        // if it's all upper case, do nothing
        if (name.matches("^[A-Z_]*$")) {
            return name;
        }

        name = getNameUsingModelPropertyNaming(name);

        // for reserved word or word starting with number, append _
        if (isReservedWord(name) || name.matches("^\\d.*")) {
            name = escapeReservedWord(name);
        }
        return name;
    }

    @Override
    public String toParamName(String name) {
        // sanitize name
        name = sanitizeName(name);

        // replace - with _ e.g. created-at => created_at
        name = name.replaceAll("-", "_");

        // if it's all upper case, do nothing
        if (name.matches("^[A-Z_]*$")) {
            return name;
        }

        // camelize(lower) the variable name
        // pet_id => petId
        name = camelize(name, LOWERCASE_FIRST_LETTER);

        // for reserved word or word starting with number, append _
        if (isReservedWord(name) || name.matches("^\\d.*")) {
            name = escapeReservedWord(name);
        }

        return name;
    }

    @Override
    public String escapeReservedWord(String name) {
        if (this.reservedWordsMappings().containsKey(name)) {
            return this.reservedWordsMappings().get(name);
        }
        return "_" + name;
    }

    /**
     * Return the example value of the property
     *
     * @param p OpenAPI property object
     * @return string presentation of the example value of the property
     */
    @Override
    public String toExampleValue(Schema p) {
        if (ModelUtils.isStringSchema(p)) {
            if (p.getExample() != null) {
                return "\"" + p.getExample().toString() + "\"";
            }
        } else if (ModelUtils.isBooleanSchema(p)) {
            if (p.getExample() != null) {
                return p.getExample().toString();
            }
        } else if (ModelUtils.isDateSchema(p)) {
            // TODO
        } else if (ModelUtils.isDateTimeSchema(p)) {
            // TODO
        } else if (ModelUtils.isNumberSchema(p)) {
            if (p.getExample() != null) {
                return p.getExample().toString();
            }
        } else if (ModelUtils.isIntegerSchema(p)) {
            if (p.getExample() != null) {
                return p.getExample().toString();
            }
        }

        return null;
    }

    /**
     * Return the default value of the property
     *
     * @param p OpenAPI property object
     * @return string presentation of the default value of the property
     */
    @Override
    public String toDefaultValue(Schema p) {
        if (ModelUtils.isBooleanSchema(p)) {
            if (p.getDefault() != null) {
                return p.getDefault().toString();
            }
        } else if (ModelUtils.isDateSchema(p)) {
            if (p.getDefault() != null) {
                return "\"" + p.getDefault().toString() + "\"";
            }
        } else if (ModelUtils.isDateTimeSchema(p)) {
            if (p.getDefault() != null) {
                return "\"" + p.getDefault().toString() + "\"";
            }
        } else if (ModelUtils.isNumberSchema(p)) {
            if (p.getDefault() != null) {
                if (ModelUtils.isFloatSchema(p)) { // float
                    return p.getDefault().toString() + "F";
                } else if (ModelUtils.isDoubleSchema(p)) { // double
                    return p.getDefault().toString() + "D";
                } else {    // decimal
                    return p.getDefault().toString() + "M";
                }
            }
        } else if (ModelUtils.isIntegerSchema(p)) {
            if (p.getDefault() != null) {
                return p.getDefault().toString();
            }
        } else if (ModelUtils.isStringSchema(p)) {
            if (p.getDefault() != null) {
                String _default = String.valueOf(p.getDefault());
                if (p.getEnum() == null) {
                    return "\"" + _default + "\"";
                } else {
                    // convert to enum var name later in postProcessModels
                    return _default;
                }
            }
        }

        return null;
    }

    @Override
    protected boolean isReservedWord(String word) {
        return reservedWords.contains(word);
    }


    public String getNullableType(Schema p, String type) {
        if (languageSpecificPrimitives.contains(type)) {
            if (isSupportNullable() && ModelUtils.isNullable(p) && nullableType.contains(type)) {
                return type + " option";
            } else {
                return type;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getSchemaType(Schema p) {
        String openAPIType = super.getSchemaType(p);
        String type;

        if (openAPIType == null) {
            LOGGER.error("OpenAPI Type for {} is null. Default to UNKNOWN_OPENAPI_TYPE instead.", p.getName());
            openAPIType = "UNKNOWN_OPENAPI_TYPE";
        }

        if (typeMapping.containsKey(openAPIType)) {
            type = typeMapping.get(openAPIType);
            String languageType = getNullableType(p, type);
            if (languageType != null) {
                return languageType;
            }
        } else {
            type = openAPIType;
        }

        return toModelName(type);
    }

    /**
     * Provides F# strongly typed declaration for simple arrays of some type and arrays of arrays of some type.
     *
     * @param arr The input array property
     * @return The type declaration when the type is an array of arrays.
     */
    private String getArrayTypeDeclaration(Schema arr) {
        // TODO: collection type here should be fully qualified namespace to avoid model conflicts
        // This supports arrays of arrays.
        String arrayType = typeMapping.get("array");
        StringBuilder instantiationType = new StringBuilder(arrayType);
        Schema items = ModelUtils.getSchemaItems(arr);
        String nestedType = getTypeDeclaration(items);
        // TODO: We may want to differentiate here between generics and primitive arrays.
        return nestedType + "[]";
    }

    @Override
    public String toInstantiationType(Schema p) {
        if (ModelUtils.isArraySchema(p)) {
            return getArrayTypeDeclaration(p);
        }
        return super.toInstantiationType(p);
    }

    @Override
    public String getTypeDeclaration(Schema p) {
        if (ModelUtils.isArraySchema(p)) {
            return getArrayTypeDeclaration(p);
        } else if (ModelUtils.isMapSchema(p)) {
            // Should we also support maps of maps?
            Schema inner = ModelUtils.getAdditionalProperties(p);
            return getSchemaType(p) + "<string, " + getTypeDeclaration(inner) + ">";
        }
        return super.getTypeDeclaration(p);
    }

    @Override
    public String toModelName(String name) {
        if (!StringUtils.isEmpty(modelNamePrefix)) {
            name = modelNamePrefix + "_" + name;
        }

        if (!StringUtils.isEmpty(modelNameSuffix)) {
            name = name + "_" + modelNameSuffix;
        }

        name = sanitizeName(name);

        // model name cannot use reserved keyword, e.g. return
        if (isReservedWord(name)) {
            LOGGER.warn("{} (reserved word) cannot be used as model name. Renamed to {}", name, camelize("model_" + name));
            name = "model_" + name; // e.g. return => ModelReturn (after camelize)
        }

        // model name starts with number
        if (name.matches("^\\d.*")) {
            LOGGER.warn("{} (model name starts with number) cannot be used as model name. Renamed to {}", name,
                    camelize("model_" + name));
            name = "model_" + name; // e.g. 200Response => Model200Response (after camelize)
        }

        // camelize the model name
        // phone_number => PhoneNumber
        return camelize(name);
    }

    @Override
    public String apiTestFileFolder() {
        return outputFolder + File.separator + testFolder;
    }

    @Override
    public String modelTestFileFolder() {
        return outputFolder + File.separator + testFolder;
    }

    @Override
    public String toApiTestFilename(String name) {
        return toApiName(name) + "Tests";
    }

    @Override
    public String toModelTestFilename(String name) {
        return toModelName(name) + "Tests";
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
        this.projectFolder = packageName;
        this.sourceFolder = projectFolder + File.separator + "src";
        this.testFolder = projectFolder + ".Tests";
    }

    @Override
    public String toEnumValue(String value, String datatype) {
        // C# only supports enums as literals for int, int?, long, long?, byte, and byte?. All else must be treated as strings.
        // Per: https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/enum
        // The approved types for an enum are byte, sbyte, short, ushort, int, uint, long, or ulong.
        // but we're not supporting unsigned integral types or shorts.
        if (datatype.startsWith("int") || datatype.startsWith("long") || datatype.startsWith("byte")) {
            return value;
        }

        return escapeText(value);
    }

    @Override
    public String toEnumVarName(String name, String datatype) {
        if (name.length() == 0) {
            return "Empty";
        }

        // for symbol, e.g. $, #
        if (getSymbolName(name) != null) {
            return camelize(getSymbolName(name));
        }

        String enumName = sanitizeName(name);

        enumName = enumName.replaceFirst("^_", "");
        enumName = enumName.replaceFirst("_$", "");

        enumName = camelize(enumName) + "Enum";

        if (enumName.matches("\\d.*")) { // starts with number
            return "_" + enumName;
        } else {
            return enumName;
        }
    }

    @Override
    public String toEnumName(CodegenProperty property) {
        return sanitizeName(camelize(property.name)) + "Enum";
    }

    public String testPackageName() {
        return this.packageName + ".Test";
    }

    @Override
    public String escapeQuotationMark(String input) {
        // remove " to avoid code injection
        return input.replace("\"", "");
    }

    @Override
    public String escapeUnsafeCharacters(String input) {
        return input.replace("*/", "*_/").replace("/*", "/_*").replace("--", "- -");
    }

    @Override
    public boolean isDataTypeString(String dataType) {
        // also treat double/decimal/float as "string" in enum so that the values (e.g. 2.8) get double-quoted
        return "String".equalsIgnoreCase(dataType) ||
                "double?".equals(dataType) || "decimal?".equals(dataType) || "float?".equals(dataType) ||
                "double".equals(dataType) || "decimal".equals(dataType) || "float".equals(dataType);
    }

    @Override
    public void setParameterExampleValue(CodegenParameter codegenParameter) {

        // set the example value
        // if not specified in x-example, generate a default value
        // TODO need to revise how to obtain the example value
        if (codegenParameter.vendorExtensions != null && codegenParameter.vendorExtensions.containsKey("x-example")) {
            codegenParameter.example = Json.pretty(codegenParameter.vendorExtensions.get("x-example"));
        } else if (Boolean.TRUE.equals(codegenParameter.isBoolean)) {
            codegenParameter.example = "true";
        } else if (Boolean.TRUE.equals(codegenParameter.isLong)) {
            codegenParameter.example = "789";
        } else if (Boolean.TRUE.equals(codegenParameter.isInteger)) {
            codegenParameter.example = "56";
        } else if (Boolean.TRUE.equals(codegenParameter.isFloat)) {
            codegenParameter.example = "3.4F";
        } else if (Boolean.TRUE.equals(codegenParameter.isDouble)) {
            codegenParameter.example = "1.2D";
        } else if (Boolean.TRUE.equals(codegenParameter.isNumber)) {
            codegenParameter.example = "8.14";
        } else if (Boolean.TRUE.equals(codegenParameter.isBinary)) {
            codegenParameter.example = "BINARY_DATA_HERE";
        } else if (Boolean.TRUE.equals(codegenParameter.isByteArray)) {
            codegenParameter.example = "BYTE_ARRAY_DATA_HERE";
        } else if (Boolean.TRUE.equals(codegenParameter.isFile)) {
            codegenParameter.example = "/path/to/file.txt";
        } else if (Boolean.TRUE.equals(codegenParameter.isDate)) {
            codegenParameter.example = "2013-10-20";
        } else if (Boolean.TRUE.equals(codegenParameter.isDateTime)) {
            codegenParameter.example = "2013-10-20T19:20:30+01:00";
        } else if (Boolean.TRUE.equals(codegenParameter.isUuid)) {
            codegenParameter.example = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        } else if (Boolean.TRUE.equals(codegenParameter.isString)) {
            codegenParameter.example = codegenParameter.paramName + "_example";
        }
    }

    @Override
    public void postProcessFile(File file, String fileType) {
        super.postProcessFile(file, fileType);
        if (file == null) {
            return;
        }

        String fsharpPostProcessFile = System.getenv("FSHARP_POST_PROCESS_FILE");
        if (StringUtils.isEmpty(fsharpPostProcessFile)) {
            return; // skip if FSHARP_POST_PROCESS_FILE env variable is not defined
        }

        // only process files with .fs extension
        if ("fs".equals(FilenameUtils.getExtension(file.toString()))) {
            this.executePostProcessor(new String[]{fsharpPostProcessFile, file.toString()});
        }
    }

    @Override
    public GeneratorLanguage generatorLanguage() {
        return GeneratorLanguage.F_SHARP;
    }
}
