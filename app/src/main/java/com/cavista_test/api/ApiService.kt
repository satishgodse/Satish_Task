package com.cavista_test.api

import com.cavista_test.main.data.SearchData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Api REST API access points
 */
@JvmSuppressWildcards
interface ApiService {

    @GET("search/1/")
    @Headers("Authorization: Client-ID 137cda6b5008a7c")
    suspend fun getSearchData(@Query("q")searchText: String): SearchData

}