package com.cavista_test.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "comment")
data class Comment(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long = 0,
        @ColumnInfo(name = "post_id")
        val postId: String? = "",
        @ColumnInfo(name = "comment")
        val comment: String? = "",
        @ColumnInfo(name = "time")
        val time: String? = ""
)
