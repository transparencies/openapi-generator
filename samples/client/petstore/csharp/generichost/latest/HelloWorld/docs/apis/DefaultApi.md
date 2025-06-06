# Org.OpenAPITools.Api.DefaultApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|--------|--------------|-------------|
| [**HelloWorldPost**](DefaultApi.md#helloworldpost) | **POST** /helloWorld | Example webhook |

<a id="helloworldpost"></a>
# **HelloWorldPost**
> void HelloWorldPost (HelloWorldPostRequest helloWorldPostRequest = null)

Example webhook

Send when an example is needed


### Parameters

| Name | Type | Description | Notes |
|------|------|-------------|-------|
| **helloWorldPostRequest** | [**HelloWorldPostRequest**](HelloWorldPostRequest.md) | Contains the details of the hello world webhook | [optional]  |

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Webhook processed |  -  |
| **500** | Webhook not processed |  -  |

[[Back to top]](#) [[Back to API list]](../../README.md#documentation-for-api-endpoints) [[Back to Model list]](../../README.md#documentation-for-models) [[Back to README]](../../README.md)

