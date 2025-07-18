package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * XmlItem
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0-SNAPSHOT")
public class XmlItem {

  private @Nullable String attributeString;

  private @Nullable BigDecimal attributeNumber;

  private @Nullable Integer attributeInteger;

  private @Nullable Boolean attributeBoolean;

  @Valid
  private List<Integer> wrappedArray = new ArrayList<>();

  private @Nullable String nameString;

  private @Nullable BigDecimal nameNumber;

  private @Nullable Integer nameInteger;

  private @Nullable Boolean nameBoolean;

  @Valid
  private List<Integer> nameArray = new ArrayList<>();

  @Valid
  private List<Integer> nameWrappedArray = new ArrayList<>();

  private @Nullable String prefixString;

  private @Nullable BigDecimal prefixNumber;

  private @Nullable Integer prefixInteger;

  private @Nullable Boolean prefixBoolean;

  @Valid
  private List<Integer> prefixArray = new ArrayList<>();

  @Valid
  private List<Integer> prefixWrappedArray = new ArrayList<>();

  private @Nullable String namespaceString;

  private @Nullable BigDecimal namespaceNumber;

  private @Nullable Integer namespaceInteger;

  private @Nullable Boolean namespaceBoolean;

  @Valid
  private List<Integer> namespaceArray = new ArrayList<>();

  @Valid
  private List<Integer> namespaceWrappedArray = new ArrayList<>();

  private @Nullable String prefixNsString;

  private @Nullable BigDecimal prefixNsNumber;

  private @Nullable Integer prefixNsInteger;

  private @Nullable Boolean prefixNsBoolean;

  @Valid
  private List<Integer> prefixNsArray = new ArrayList<>();

  @Valid
  private List<Integer> prefixNsWrappedArray = new ArrayList<>();

  public XmlItem attributeString(@Nullable String attributeString) {
    this.attributeString = attributeString;
    return this;
  }

  /**
   * Get attributeString
   * @return attributeString
   */
  
  @Schema(name = "attribute_string", example = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attribute_string")
  public @Nullable String getAttributeString() {
    return attributeString;
  }

  public void setAttributeString(@Nullable String attributeString) {
    this.attributeString = attributeString;
  }

  public XmlItem attributeNumber(@Nullable BigDecimal attributeNumber) {
    this.attributeNumber = attributeNumber;
    return this;
  }

  /**
   * Get attributeNumber
   * @return attributeNumber
   */
  @Valid 
  @Schema(name = "attribute_number", example = "1.234", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attribute_number")
  public @Nullable BigDecimal getAttributeNumber() {
    return attributeNumber;
  }

  public void setAttributeNumber(@Nullable BigDecimal attributeNumber) {
    this.attributeNumber = attributeNumber;
  }

  public XmlItem attributeInteger(@Nullable Integer attributeInteger) {
    this.attributeInteger = attributeInteger;
    return this;
  }

  /**
   * Get attributeInteger
   * @return attributeInteger
   */
  
  @Schema(name = "attribute_integer", example = "-2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attribute_integer")
  public @Nullable Integer getAttributeInteger() {
    return attributeInteger;
  }

  public void setAttributeInteger(@Nullable Integer attributeInteger) {
    this.attributeInteger = attributeInteger;
  }

  public XmlItem attributeBoolean(@Nullable Boolean attributeBoolean) {
    this.attributeBoolean = attributeBoolean;
    return this;
  }

  /**
   * Get attributeBoolean
   * @return attributeBoolean
   */
  
  @Schema(name = "attribute_boolean", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attribute_boolean")
  public @Nullable Boolean getAttributeBoolean() {
    return attributeBoolean;
  }

  public void setAttributeBoolean(@Nullable Boolean attributeBoolean) {
    this.attributeBoolean = attributeBoolean;
  }

  public XmlItem wrappedArray(List<Integer> wrappedArray) {
    this.wrappedArray = wrappedArray;
    return this;
  }

  public XmlItem addWrappedArrayItem(Integer wrappedArrayItem) {
    if (this.wrappedArray == null) {
      this.wrappedArray = new ArrayList<>();
    }
    this.wrappedArray.add(wrappedArrayItem);
    return this;
  }

  /**
   * Get wrappedArray
   * @return wrappedArray
   */
  
  @Schema(name = "wrapped_array", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("wrapped_array")
  public List<Integer> getWrappedArray() {
    return wrappedArray;
  }

  public void setWrappedArray(List<Integer> wrappedArray) {
    this.wrappedArray = wrappedArray;
  }

  public XmlItem nameString(@Nullable String nameString) {
    this.nameString = nameString;
    return this;
  }

  /**
   * Get nameString
   * @return nameString
   */
  
  @Schema(name = "name_string", example = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name_string")
  public @Nullable String getNameString() {
    return nameString;
  }

  public void setNameString(@Nullable String nameString) {
    this.nameString = nameString;
  }

  public XmlItem nameNumber(@Nullable BigDecimal nameNumber) {
    this.nameNumber = nameNumber;
    return this;
  }

  /**
   * Get nameNumber
   * @return nameNumber
   */
  @Valid 
  @Schema(name = "name_number", example = "1.234", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name_number")
  public @Nullable BigDecimal getNameNumber() {
    return nameNumber;
  }

  public void setNameNumber(@Nullable BigDecimal nameNumber) {
    this.nameNumber = nameNumber;
  }

  public XmlItem nameInteger(@Nullable Integer nameInteger) {
    this.nameInteger = nameInteger;
    return this;
  }

  /**
   * Get nameInteger
   * @return nameInteger
   */
  
  @Schema(name = "name_integer", example = "-2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name_integer")
  public @Nullable Integer getNameInteger() {
    return nameInteger;
  }

  public void setNameInteger(@Nullable Integer nameInteger) {
    this.nameInteger = nameInteger;
  }

  public XmlItem nameBoolean(@Nullable Boolean nameBoolean) {
    this.nameBoolean = nameBoolean;
    return this;
  }

  /**
   * Get nameBoolean
   * @return nameBoolean
   */
  
  @Schema(name = "name_boolean", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name_boolean")
  public @Nullable Boolean getNameBoolean() {
    return nameBoolean;
  }

  public void setNameBoolean(@Nullable Boolean nameBoolean) {
    this.nameBoolean = nameBoolean;
  }

  public XmlItem nameArray(List<Integer> nameArray) {
    this.nameArray = nameArray;
    return this;
  }

  public XmlItem addNameArrayItem(Integer nameArrayItem) {
    if (this.nameArray == null) {
      this.nameArray = new ArrayList<>();
    }
    this.nameArray.add(nameArrayItem);
    return this;
  }

  /**
   * Get nameArray
   * @return nameArray
   */
  
  @Schema(name = "name_array", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name_array")
  public List<Integer> getNameArray() {
    return nameArray;
  }

  public void setNameArray(List<Integer> nameArray) {
    this.nameArray = nameArray;
  }

  public XmlItem nameWrappedArray(List<Integer> nameWrappedArray) {
    this.nameWrappedArray = nameWrappedArray;
    return this;
  }

  public XmlItem addNameWrappedArrayItem(Integer nameWrappedArrayItem) {
    if (this.nameWrappedArray == null) {
      this.nameWrappedArray = new ArrayList<>();
    }
    this.nameWrappedArray.add(nameWrappedArrayItem);
    return this;
  }

  /**
   * Get nameWrappedArray
   * @return nameWrappedArray
   */
  
  @Schema(name = "name_wrapped_array", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name_wrapped_array")
  public List<Integer> getNameWrappedArray() {
    return nameWrappedArray;
  }

  public void setNameWrappedArray(List<Integer> nameWrappedArray) {
    this.nameWrappedArray = nameWrappedArray;
  }

  public XmlItem prefixString(@Nullable String prefixString) {
    this.prefixString = prefixString;
    return this;
  }

  /**
   * Get prefixString
   * @return prefixString
   */
  
  @Schema(name = "prefix_string", example = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prefix_string")
  public @Nullable String getPrefixString() {
    return prefixString;
  }

  public void setPrefixString(@Nullable String prefixString) {
    this.prefixString = prefixString;
  }

  public XmlItem prefixNumber(@Nullable BigDecimal prefixNumber) {
    this.prefixNumber = prefixNumber;
    return this;
  }

  /**
   * Get prefixNumber
   * @return prefixNumber
   */
  @Valid 
  @Schema(name = "prefix_number", example = "1.234", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prefix_number")
  public @Nullable BigDecimal getPrefixNumber() {
    return prefixNumber;
  }

  public void setPrefixNumber(@Nullable BigDecimal prefixNumber) {
    this.prefixNumber = prefixNumber;
  }

  public XmlItem prefixInteger(@Nullable Integer prefixInteger) {
    this.prefixInteger = prefixInteger;
    return this;
  }

  /**
   * Get prefixInteger
   * @return prefixInteger
   */
  
  @Schema(name = "prefix_integer", example = "-2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prefix_integer")
  public @Nullable Integer getPrefixInteger() {
    return prefixInteger;
  }

  public void setPrefixInteger(@Nullable Integer prefixInteger) {
    this.prefixInteger = prefixInteger;
  }

  public XmlItem prefixBoolean(@Nullable Boolean prefixBoolean) {
    this.prefixBoolean = prefixBoolean;
    return this;
  }

  /**
   * Get prefixBoolean
   * @return prefixBoolean
   */
  
  @Schema(name = "prefix_boolean", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prefix_boolean")
  public @Nullable Boolean getPrefixBoolean() {
    return prefixBoolean;
  }

  public void setPrefixBoolean(@Nullable Boolean prefixBoolean) {
    this.prefixBoolean = prefixBoolean;
  }

  public XmlItem prefixArray(List<Integer> prefixArray) {
    this.prefixArray = prefixArray;
    return this;
  }

  public XmlItem addPrefixArrayItem(Integer prefixArrayItem) {
    if (this.prefixArray == null) {
      this.prefixArray = new ArrayList<>();
    }
    this.prefixArray.add(prefixArrayItem);
    return this;
  }

  /**
   * Get prefixArray
   * @return prefixArray
   */
  
  @Schema(name = "prefix_array", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prefix_array")
  public List<Integer> getPrefixArray() {
    return prefixArray;
  }

  public void setPrefixArray(List<Integer> prefixArray) {
    this.prefixArray = prefixArray;
  }

  public XmlItem prefixWrappedArray(List<Integer> prefixWrappedArray) {
    this.prefixWrappedArray = prefixWrappedArray;
    return this;
  }

  public XmlItem addPrefixWrappedArrayItem(Integer prefixWrappedArrayItem) {
    if (this.prefixWrappedArray == null) {
      this.prefixWrappedArray = new ArrayList<>();
    }
    this.prefixWrappedArray.add(prefixWrappedArrayItem);
    return this;
  }

  /**
   * Get prefixWrappedArray
   * @return prefixWrappedArray
   */
  
  @Schema(name = "prefix_wrapped_array", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prefix_wrapped_array")
  public List<Integer> getPrefixWrappedArray() {
    return prefixWrappedArray;
  }

  public void setPrefixWrappedArray(List<Integer> prefixWrappedArray) {
    this.prefixWrappedArray = prefixWrappedArray;
  }

  public XmlItem namespaceString(@Nullable String namespaceString) {
    this.namespaceString = namespaceString;
    return this;
  }

  /**
   * Get namespaceString
   * @return namespaceString
   */
  
  @Schema(name = "namespace_string", example = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("namespace_string")
  public @Nullable String getNamespaceString() {
    return namespaceString;
  }

  public void setNamespaceString(@Nullable String namespaceString) {
    this.namespaceString = namespaceString;
  }

  public XmlItem namespaceNumber(@Nullable BigDecimal namespaceNumber) {
    this.namespaceNumber = namespaceNumber;
    return this;
  }

  /**
   * Get namespaceNumber
   * @return namespaceNumber
   */
  @Valid 
  @Schema(name = "namespace_number", example = "1.234", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("namespace_number")
  public @Nullable BigDecimal getNamespaceNumber() {
    return namespaceNumber;
  }

  public void setNamespaceNumber(@Nullable BigDecimal namespaceNumber) {
    this.namespaceNumber = namespaceNumber;
  }

  public XmlItem namespaceInteger(@Nullable Integer namespaceInteger) {
    this.namespaceInteger = namespaceInteger;
    return this;
  }

  /**
   * Get namespaceInteger
   * @return namespaceInteger
   */
  
  @Schema(name = "namespace_integer", example = "-2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("namespace_integer")
  public @Nullable Integer getNamespaceInteger() {
    return namespaceInteger;
  }

  public void setNamespaceInteger(@Nullable Integer namespaceInteger) {
    this.namespaceInteger = namespaceInteger;
  }

  public XmlItem namespaceBoolean(@Nullable Boolean namespaceBoolean) {
    this.namespaceBoolean = namespaceBoolean;
    return this;
  }

  /**
   * Get namespaceBoolean
   * @return namespaceBoolean
   */
  
  @Schema(name = "namespace_boolean", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("namespace_boolean")
  public @Nullable Boolean getNamespaceBoolean() {
    return namespaceBoolean;
  }

  public void setNamespaceBoolean(@Nullable Boolean namespaceBoolean) {
    this.namespaceBoolean = namespaceBoolean;
  }

  public XmlItem namespaceArray(List<Integer> namespaceArray) {
    this.namespaceArray = namespaceArray;
    return this;
  }

  public XmlItem addNamespaceArrayItem(Integer namespaceArrayItem) {
    if (this.namespaceArray == null) {
      this.namespaceArray = new ArrayList<>();
    }
    this.namespaceArray.add(namespaceArrayItem);
    return this;
  }

  /**
   * Get namespaceArray
   * @return namespaceArray
   */
  
  @Schema(name = "namespace_array", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("namespace_array")
  public List<Integer> getNamespaceArray() {
    return namespaceArray;
  }

  public void setNamespaceArray(List<Integer> namespaceArray) {
    this.namespaceArray = namespaceArray;
  }

  public XmlItem namespaceWrappedArray(List<Integer> namespaceWrappedArray) {
    this.namespaceWrappedArray = namespaceWrappedArray;
    return this;
  }

  public XmlItem addNamespaceWrappedArrayItem(Integer namespaceWrappedArrayItem) {
    if (this.namespaceWrappedArray == null) {
      this.namespaceWrappedArray = new ArrayList<>();
    }
    this.namespaceWrappedArray.add(namespaceWrappedArrayItem);
    return this;
  }

  /**
   * Get namespaceWrappedArray
   * @return namespaceWrappedArray
   */
  
  @Schema(name = "namespace_wrapped_array", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("namespace_wrapped_array")
  public List<Integer> getNamespaceWrappedArray() {
    return namespaceWrappedArray;
  }

  public void setNamespaceWrappedArray(List<Integer> namespaceWrappedArray) {
    this.namespaceWrappedArray = namespaceWrappedArray;
  }

  public XmlItem prefixNsString(@Nullable String prefixNsString) {
    this.prefixNsString = prefixNsString;
    return this;
  }

  /**
   * Get prefixNsString
   * @return prefixNsString
   */
  
  @Schema(name = "prefix_ns_string", example = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prefix_ns_string")
  public @Nullable String getPrefixNsString() {
    return prefixNsString;
  }

  public void setPrefixNsString(@Nullable String prefixNsString) {
    this.prefixNsString = prefixNsString;
  }

  public XmlItem prefixNsNumber(@Nullable BigDecimal prefixNsNumber) {
    this.prefixNsNumber = prefixNsNumber;
    return this;
  }

  /**
   * Get prefixNsNumber
   * @return prefixNsNumber
   */
  @Valid 
  @Schema(name = "prefix_ns_number", example = "1.234", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prefix_ns_number")
  public @Nullable BigDecimal getPrefixNsNumber() {
    return prefixNsNumber;
  }

  public void setPrefixNsNumber(@Nullable BigDecimal prefixNsNumber) {
    this.prefixNsNumber = prefixNsNumber;
  }

  public XmlItem prefixNsInteger(@Nullable Integer prefixNsInteger) {
    this.prefixNsInteger = prefixNsInteger;
    return this;
  }

  /**
   * Get prefixNsInteger
   * @return prefixNsInteger
   */
  
  @Schema(name = "prefix_ns_integer", example = "-2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prefix_ns_integer")
  public @Nullable Integer getPrefixNsInteger() {
    return prefixNsInteger;
  }

  public void setPrefixNsInteger(@Nullable Integer prefixNsInteger) {
    this.prefixNsInteger = prefixNsInteger;
  }

  public XmlItem prefixNsBoolean(@Nullable Boolean prefixNsBoolean) {
    this.prefixNsBoolean = prefixNsBoolean;
    return this;
  }

  /**
   * Get prefixNsBoolean
   * @return prefixNsBoolean
   */
  
  @Schema(name = "prefix_ns_boolean", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prefix_ns_boolean")
  public @Nullable Boolean getPrefixNsBoolean() {
    return prefixNsBoolean;
  }

  public void setPrefixNsBoolean(@Nullable Boolean prefixNsBoolean) {
    this.prefixNsBoolean = prefixNsBoolean;
  }

  public XmlItem prefixNsArray(List<Integer> prefixNsArray) {
    this.prefixNsArray = prefixNsArray;
    return this;
  }

  public XmlItem addPrefixNsArrayItem(Integer prefixNsArrayItem) {
    if (this.prefixNsArray == null) {
      this.prefixNsArray = new ArrayList<>();
    }
    this.prefixNsArray.add(prefixNsArrayItem);
    return this;
  }

  /**
   * Get prefixNsArray
   * @return prefixNsArray
   */
  
  @Schema(name = "prefix_ns_array", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prefix_ns_array")
  public List<Integer> getPrefixNsArray() {
    return prefixNsArray;
  }

  public void setPrefixNsArray(List<Integer> prefixNsArray) {
    this.prefixNsArray = prefixNsArray;
  }

  public XmlItem prefixNsWrappedArray(List<Integer> prefixNsWrappedArray) {
    this.prefixNsWrappedArray = prefixNsWrappedArray;
    return this;
  }

  public XmlItem addPrefixNsWrappedArrayItem(Integer prefixNsWrappedArrayItem) {
    if (this.prefixNsWrappedArray == null) {
      this.prefixNsWrappedArray = new ArrayList<>();
    }
    this.prefixNsWrappedArray.add(prefixNsWrappedArrayItem);
    return this;
  }

  /**
   * Get prefixNsWrappedArray
   * @return prefixNsWrappedArray
   */
  
  @Schema(name = "prefix_ns_wrapped_array", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prefix_ns_wrapped_array")
  public List<Integer> getPrefixNsWrappedArray() {
    return prefixNsWrappedArray;
  }

  public void setPrefixNsWrappedArray(List<Integer> prefixNsWrappedArray) {
    this.prefixNsWrappedArray = prefixNsWrappedArray;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    XmlItem xmlItem = (XmlItem) o;
    return Objects.equals(this.attributeString, xmlItem.attributeString) &&
        Objects.equals(this.attributeNumber, xmlItem.attributeNumber) &&
        Objects.equals(this.attributeInteger, xmlItem.attributeInteger) &&
        Objects.equals(this.attributeBoolean, xmlItem.attributeBoolean) &&
        Objects.equals(this.wrappedArray, xmlItem.wrappedArray) &&
        Objects.equals(this.nameString, xmlItem.nameString) &&
        Objects.equals(this.nameNumber, xmlItem.nameNumber) &&
        Objects.equals(this.nameInteger, xmlItem.nameInteger) &&
        Objects.equals(this.nameBoolean, xmlItem.nameBoolean) &&
        Objects.equals(this.nameArray, xmlItem.nameArray) &&
        Objects.equals(this.nameWrappedArray, xmlItem.nameWrappedArray) &&
        Objects.equals(this.prefixString, xmlItem.prefixString) &&
        Objects.equals(this.prefixNumber, xmlItem.prefixNumber) &&
        Objects.equals(this.prefixInteger, xmlItem.prefixInteger) &&
        Objects.equals(this.prefixBoolean, xmlItem.prefixBoolean) &&
        Objects.equals(this.prefixArray, xmlItem.prefixArray) &&
        Objects.equals(this.prefixWrappedArray, xmlItem.prefixWrappedArray) &&
        Objects.equals(this.namespaceString, xmlItem.namespaceString) &&
        Objects.equals(this.namespaceNumber, xmlItem.namespaceNumber) &&
        Objects.equals(this.namespaceInteger, xmlItem.namespaceInteger) &&
        Objects.equals(this.namespaceBoolean, xmlItem.namespaceBoolean) &&
        Objects.equals(this.namespaceArray, xmlItem.namespaceArray) &&
        Objects.equals(this.namespaceWrappedArray, xmlItem.namespaceWrappedArray) &&
        Objects.equals(this.prefixNsString, xmlItem.prefixNsString) &&
        Objects.equals(this.prefixNsNumber, xmlItem.prefixNsNumber) &&
        Objects.equals(this.prefixNsInteger, xmlItem.prefixNsInteger) &&
        Objects.equals(this.prefixNsBoolean, xmlItem.prefixNsBoolean) &&
        Objects.equals(this.prefixNsArray, xmlItem.prefixNsArray) &&
        Objects.equals(this.prefixNsWrappedArray, xmlItem.prefixNsWrappedArray);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributeString, attributeNumber, attributeInteger, attributeBoolean, wrappedArray, nameString, nameNumber, nameInteger, nameBoolean, nameArray, nameWrappedArray, prefixString, prefixNumber, prefixInteger, prefixBoolean, prefixArray, prefixWrappedArray, namespaceString, namespaceNumber, namespaceInteger, namespaceBoolean, namespaceArray, namespaceWrappedArray, prefixNsString, prefixNsNumber, prefixNsInteger, prefixNsBoolean, prefixNsArray, prefixNsWrappedArray);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class XmlItem {\n");
    sb.append("    attributeString: ").append(toIndentedString(attributeString)).append("\n");
    sb.append("    attributeNumber: ").append(toIndentedString(attributeNumber)).append("\n");
    sb.append("    attributeInteger: ").append(toIndentedString(attributeInteger)).append("\n");
    sb.append("    attributeBoolean: ").append(toIndentedString(attributeBoolean)).append("\n");
    sb.append("    wrappedArray: ").append(toIndentedString(wrappedArray)).append("\n");
    sb.append("    nameString: ").append(toIndentedString(nameString)).append("\n");
    sb.append("    nameNumber: ").append(toIndentedString(nameNumber)).append("\n");
    sb.append("    nameInteger: ").append(toIndentedString(nameInteger)).append("\n");
    sb.append("    nameBoolean: ").append(toIndentedString(nameBoolean)).append("\n");
    sb.append("    nameArray: ").append(toIndentedString(nameArray)).append("\n");
    sb.append("    nameWrappedArray: ").append(toIndentedString(nameWrappedArray)).append("\n");
    sb.append("    prefixString: ").append(toIndentedString(prefixString)).append("\n");
    sb.append("    prefixNumber: ").append(toIndentedString(prefixNumber)).append("\n");
    sb.append("    prefixInteger: ").append(toIndentedString(prefixInteger)).append("\n");
    sb.append("    prefixBoolean: ").append(toIndentedString(prefixBoolean)).append("\n");
    sb.append("    prefixArray: ").append(toIndentedString(prefixArray)).append("\n");
    sb.append("    prefixWrappedArray: ").append(toIndentedString(prefixWrappedArray)).append("\n");
    sb.append("    namespaceString: ").append(toIndentedString(namespaceString)).append("\n");
    sb.append("    namespaceNumber: ").append(toIndentedString(namespaceNumber)).append("\n");
    sb.append("    namespaceInteger: ").append(toIndentedString(namespaceInteger)).append("\n");
    sb.append("    namespaceBoolean: ").append(toIndentedString(namespaceBoolean)).append("\n");
    sb.append("    namespaceArray: ").append(toIndentedString(namespaceArray)).append("\n");
    sb.append("    namespaceWrappedArray: ").append(toIndentedString(namespaceWrappedArray)).append("\n");
    sb.append("    prefixNsString: ").append(toIndentedString(prefixNsString)).append("\n");
    sb.append("    prefixNsNumber: ").append(toIndentedString(prefixNsNumber)).append("\n");
    sb.append("    prefixNsInteger: ").append(toIndentedString(prefixNsInteger)).append("\n");
    sb.append("    prefixNsBoolean: ").append(toIndentedString(prefixNsBoolean)).append("\n");
    sb.append("    prefixNsArray: ").append(toIndentedString(prefixNsArray)).append("\n");
    sb.append("    prefixNsWrappedArray: ").append(toIndentedString(prefixNsWrappedArray)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

