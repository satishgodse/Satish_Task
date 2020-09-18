package com.cavista_test.main.data


import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)