package com.cavista_test.details.data

import com.cavista_test.database.AppDatabase
import com.cavista_test.database.Comment

class ImageDetailsRepository constructor(private val appDatabase: AppDatabase) {

    suspend fun insertUpdateComments(comment: Comment) = appDatabase.commentDao().insertUpdateComments(comment)

    fun getCommentsByPostId(postId : String?) = appDatabase.commentDao().getCommentsByPostId(postId)
}