package com.jualo.moviebox.api.responses

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
        @SerializedName("status_code") val statusCode: Int,
        @SerializedName("status_message") val statusMessage: String
)