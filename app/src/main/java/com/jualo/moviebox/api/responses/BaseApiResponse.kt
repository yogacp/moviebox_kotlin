package com.jualo.moviebox.api.responses

import com.google.gson.annotations.SerializedName

data class BaseApiResponse<T>(
        @SerializedName("page") val page: Int,
        @SerializedName("total_results") val totalResults: Int,
        @SerializedName("total_pages") val totalPages: Int,
        @SerializedName("results") val results: List<T>
)