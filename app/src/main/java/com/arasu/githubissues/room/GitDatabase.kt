package com.arasu.githubissues.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arasu.githubissues.room.comments.CommentsCacheEntity
import com.arasu.githubissues.room.issues.GitEntity

/**
 * Created by Arasu on 10-05-2021.
 */
@Database(entities = [GitEntity::class, CommentsCacheEntity::class], version = 1)
abstract class GitDatabase : RoomDatabase() {
    abstract fun gitDao(): GitDao

    companion object {
        val DATA_BASE: String = "github_db"
    }
}