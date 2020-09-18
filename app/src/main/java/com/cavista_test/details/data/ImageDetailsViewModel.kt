package com.cavista_test.details.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cavista_test.database.Comment
import kotlinx.coroutines.launch

class ImageDetailsViewModel constructor(private val imageDetailsRepository: ImageDetailsRepository): ViewModel() {

    fun insertUpdateComments(cart: Comment) {
        viewModelScope.launch {
            try {
                imageDetailsRepository.insertUpdateComments(cart)
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getCommentsByPostId(postId : String?) = imageDetailsRepository.getCommentsByPostId(postId)

}