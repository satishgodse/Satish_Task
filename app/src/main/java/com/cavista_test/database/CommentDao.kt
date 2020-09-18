package com.cavista_test.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentDao {
    @Query("SELECT * FROM comment")
    fun getAllComments(): LiveData<List<Comment>>

    @Query("SELECT * FROM comment WHERE post_id = :postId ORDER BY time DESC")
    fun getCommentsByPostId(postId:String?): LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpdateComments(comment: Comment)

}