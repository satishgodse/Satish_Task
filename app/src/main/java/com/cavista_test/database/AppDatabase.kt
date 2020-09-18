package com.cavista_test.database

import android.content.Context
import androidx.room.*
import com.cavista_test.utils.Constant

/**
 * The Room database for this app
 */
@Database(entities = [Comment::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun commentDao():  CommentDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, Constant.DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                    }).allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build()
        }
    }
}
