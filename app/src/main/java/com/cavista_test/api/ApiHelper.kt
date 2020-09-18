package com.cavista_test.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getSearchData(searchText: String) = apiService.getSearchData(searchText)
}