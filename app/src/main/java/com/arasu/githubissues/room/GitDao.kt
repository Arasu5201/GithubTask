package com.arasu.githubissues.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arasu.githubissues.room.comments.CommentsCacheEntity
import com.arasu.githubissues.room.issues.GitEntity

/**
 * Created by Arasu on 10-05-2021.
 */
@Dao
interface GitDao {

    @Query("SELECT * FROM githubissues")
    suspend fun getIssues(): List<GitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIssues(githubEntity: GitEntity): Long

    @Query("SELECT * FROM githubcomments WHERE issue_url LIKE :number")
    suspend fun getComments(number: String): List<CommentsCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(githubEntity: CommentsCacheEntity): Long
}