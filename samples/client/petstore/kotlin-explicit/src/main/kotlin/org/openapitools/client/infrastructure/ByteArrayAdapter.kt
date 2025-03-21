package org.openapitools.client.infrastructure

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

public class ByteArrayAdapter {
    @ToJson
    public fun toJson(data: ByteArray): String = String(data)

    @FromJson
    public fun fromJson(data: String): ByteArray = data.toByteArray()
}
