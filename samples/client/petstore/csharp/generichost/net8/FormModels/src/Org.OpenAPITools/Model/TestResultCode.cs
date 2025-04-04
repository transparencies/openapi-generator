// <auto-generated>
/*
 * OpenAPI Petstore
 *
 * This spec is mainly for testing Petstore server and contains fake endpoints, models. Please do not use this for any other purpose. Special characters: \" \\
 *
 * The version of the OpenAPI document: 1.0.0
 * Generated by: https://github.com/openapitools/openapi-generator.git
 */

using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.IO;
using System.Text;
using System.Text.RegularExpressions;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.ComponentModel.DataAnnotations;
using OpenAPIClientUtils = Org.OpenAPITools.Client.ClientUtils;
using Org.OpenAPITools.Client;

namespace Org.OpenAPITools.Model
{
    /// <summary>
    /// Result code
    /// </summary>
    /// <value>Result code</value>
    public enum TestResultCode
    {
        /// <summary>
        /// Enum APPROVED for value: APPROVED
        /// </summary>
        APPROVED = 1,

        /// <summary>
        /// Enum MANUALAPPROVALREQUIRED for value: MANUAL_APPROVAL_REQUIRED
        /// </summary>
        MANUALAPPROVALREQUIRED = 2
    }

    /// <summary>
    /// Converts <see cref="TestResultCode"/> to and from the JSON value
    /// </summary>
    public static class TestResultCodeValueConverter
    {
        /// <summary>
        /// Parses a given value to <see cref="TestResultCode"/>
        /// </summary>
        /// <param name="value"></param>
        /// <returns></returns>
        public static TestResultCode FromString(string value)
        {
            if (value.Equals("APPROVED"))
                return TestResultCode.APPROVED;

            if (value.Equals("MANUAL_APPROVAL_REQUIRED"))
                return TestResultCode.MANUALAPPROVALREQUIRED;

            throw new NotImplementedException($"Could not convert value to type TestResultCode: '{value}'");
        }

        /// <summary>
        /// Parses a given value to <see cref="TestResultCode"/>
        /// </summary>
        /// <param name="value"></param>
        /// <returns></returns>
        public static TestResultCode? FromStringOrDefault(string value)
        {
            if (value.Equals("APPROVED"))
                return TestResultCode.APPROVED;

            if (value.Equals("MANUAL_APPROVAL_REQUIRED"))
                return TestResultCode.MANUALAPPROVALREQUIRED;

            return null;
        }

        /// <summary>
        /// Converts the <see cref="TestResultCode"/> to the json value
        /// </summary>
        /// <param name="value"></param>
        /// <returns></returns>
        /// <exception cref="NotImplementedException"></exception>
        public static string ToJsonValue(TestResultCode value)
        {
            if (value == TestResultCode.APPROVED)
                return "APPROVED";

            if (value == TestResultCode.MANUALAPPROVALREQUIRED)
                return "MANUAL_APPROVAL_REQUIRED";

            throw new NotImplementedException($"Value could not be handled: '{value}'");
        }
    }

    /// <summary>
    /// A Json converter for type <see cref="TestResultCode"/>
    /// </summary>
    /// <exception cref="NotImplementedException"></exception>
    public class TestResultCodeJsonConverter : JsonConverter<TestResultCode>
    {
        /// <summary>
        /// Returns a  from the Json object
        /// </summary>
        /// <param name="reader"></param>
        /// <param name="typeToConvert"></param>
        /// <param name="options"></param>
        /// <returns></returns>
        public override TestResultCode Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
        {
            string rawValue = reader.GetString();

            TestResultCode? result = rawValue == null
                ? null
                : TestResultCodeValueConverter.FromStringOrDefault(rawValue);

            if (result != null)
                return result.Value;

            throw new JsonException();
        }

        /// <summary>
        /// Writes the TestResultCode to the json writer
        /// </summary>
        /// <param name="writer"></param>
        /// <param name="testResultCode"></param>
        /// <param name="options"></param>
        public override void Write(Utf8JsonWriter writer, TestResultCode testResultCode, JsonSerializerOptions options)
        {
            writer.WriteStringValue(testResultCode.ToString());
        }
    }

    /// <summary>
    /// A Json converter for type <see cref="TestResultCode"/>
    /// </summary>
    public class TestResultCodeNullableJsonConverter : JsonConverter<TestResultCode?>
    {
        /// <summary>
        /// Returns a TestResultCode from the Json object
        /// </summary>
        /// <param name="reader"></param>
        /// <param name="typeToConvert"></param>
        /// <param name="options"></param>
        /// <returns></returns>
        public override TestResultCode? Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
        {
            string rawValue = reader.GetString();

            TestResultCode? result = rawValue == null
                ? null
                : TestResultCodeValueConverter.FromStringOrDefault(rawValue);

            if (result != null)
                return result.Value;

            throw new JsonException();
        }

        /// <summary>
        /// Writes the DateTime to the json writer
        /// </summary>
        /// <param name="writer"></param>
        /// <param name="testResultCode"></param>
        /// <param name="options"></param>
        public override void Write(Utf8JsonWriter writer, TestResultCode? testResultCode, JsonSerializerOptions options)
        {
            writer.WriteStringValue(testResultCode?.ToString() ?? "null");
        }
    }
}
