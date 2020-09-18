package com.cavista_test.details.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cavista_test.database.AppDatabase

class ImageViewModelFactory(private val appDatabase: AppDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ImageDetailsViewModel::class.java)) {
            return ImageDetailsViewModel(ImageDetailsRepository(appDatabase)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}