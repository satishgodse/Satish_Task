package com.cavista_test.main.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.cavista_test.api.Resource
import kotlinx.coroutines.Dispatchers
import java.io.IOException

class SearchViewModel constructor(private val searchRepository: SearchRepository): ViewModel() {

    fun getSearchData(searchText: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = searchRepository.getSearchData(searchText)))
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            emit(Resource.networkError(data = null, message = ioException.message ?: "Network Error Occurred!"))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}