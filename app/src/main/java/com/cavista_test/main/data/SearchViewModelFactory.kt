package com.cavista_test.main.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cavista_test.api.ApiHelper

class SearchViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(SearchRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}