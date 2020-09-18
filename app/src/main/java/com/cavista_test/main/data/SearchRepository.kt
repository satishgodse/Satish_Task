package com.cavista_test.main.data

import com.cavista_test.api.ApiHelper

class SearchRepository constructor(private val apiHelper: ApiHelper) {

    suspend fun getSearchData(searchText: String) = apiHelper.getSearchData(searchText)
}