/*
 * OpenAPI Petstore
 * This spec is mainly for testing Petstore server and contains fake endpoints, models. Please do not use this for any other purpose. Special characters: \" \\
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import org.openapitools.client.ApiClient;
/**
 * AppleReq
 */
@JsonPropertyOrder({
  AppleReq.JSON_PROPERTY_CULTIVAR,
  AppleReq.JSON_PROPERTY_MEALY
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.15.0-SNAPSHOT")
public class AppleReq {
  public static final String JSON_PROPERTY_CULTIVAR = "cultivar";
  @javax.annotation.Nonnull
  private String cultivar;

  public static final String JSON_PROPERTY_MEALY = "mealy";
  @javax.annotation.Nullable
  private Boolean mealy;

  public AppleReq() { 
  }

  public AppleReq cultivar(@javax.annotation.Nonnull String cultivar) {
    this.cultivar = cultivar;
    return this;
  }

  /**
   * Get cultivar
   * @return cultivar
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CULTIVAR)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getCultivar() {
    return cultivar;
  }


  @JsonProperty(JSON_PROPERTY_CULTIVAR)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCultivar(@javax.annotation.Nonnull String cultivar) {
    this.cultivar = cultivar;
  }


  public AppleReq mealy(@javax.annotation.Nullable Boolean mealy) {
    this.mealy = mealy;
    return this;
  }

  /**
   * Get mealy
   * @return mealy
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_MEALY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getMealy() {
    return mealy;
  }


  @JsonProperty(JSON_PROPERTY_MEALY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMealy(@javax.annotation.Nullable Boolean mealy) {
    this.mealy = mealy;
  }


  /**
   * Return true if this appleReq object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o, false, null, true);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppleReq {\n");
    sb.append("    cultivar: ").append(toIndentedString(cultivar)).append("\n");
    sb.append("    mealy: ").append(toIndentedString(mealy)).append("\n");
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

  /**
   * Convert the instance into URL query string.
   *
   * @return URL query string
   */
  public String toUrlQueryString() {
    return toUrlQueryString(null);
  }

  /**
   * Convert the instance into URL query string.
   *
   * @param prefix prefix of the query string
   * @return URL query string
   */
  public String toUrlQueryString(String prefix) {
    String suffix = "";
    String containerSuffix = "";
    String containerPrefix = "";
    if (prefix == null) {
      // style=form, explode=true, e.g. /pet?name=cat&type=manx
      prefix = "";
    } else {
      // deepObject style e.g. /pet?id[name]=cat&id[type]=manx
      prefix = prefix + "[";
      suffix = "]";
      containerSuffix = "]";
      containerPrefix = "[";
    }

    StringJoiner joiner = new StringJoiner("&");

    // add `cultivar` to the URL query string
    if (getCultivar() != null) {
      joiner.add(String.format("%scultivar%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getCultivar()))));
    }

    // add `mealy` to the URL query string
    if (getMealy() != null) {
      joiner.add(String.format("%smealy%s=%s", prefix, suffix, ApiClient.urlEncode(ApiClient.valueToString(getMealy()))));
    }

    return joiner.toString();
  }

    public static class Builder {

    private AppleReq instance;

    public Builder() {
      this(new AppleReq());
    }

    protected Builder(AppleReq instance) {
      this.instance = instance;
    }

    public AppleReq.Builder cultivar(String cultivar) {
      this.instance.cultivar = cultivar;
      return this;
    }
    public AppleReq.Builder mealy(Boolean mealy) {
      this.instance.mealy = mealy;
      return this;
    }


    /**
    * returns a built AppleReq instance.
    *
    * The builder is not reusable.
    */
    public AppleReq build() {
      try {
        return this.instance;
      } finally {
        // ensure that this.instance is not reused
        this.instance = null;
      }
    }

    @Override
    public String toString() {
      return getClass() + "=(" + instance + ")";
    }
  }

  /**
  * Create a builder with no initialized field.
  */
  public static AppleReq.Builder builder() {
    return new AppleReq.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AppleReq.Builder toBuilder() {
    return new AppleReq.Builder()
      .cultivar(getCultivar())
      .mealy(getMealy());
  }

}

