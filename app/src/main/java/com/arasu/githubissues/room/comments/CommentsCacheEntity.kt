package com.arasu.githubissues.room.comments

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arasu.githubissues.retrofit.UserEntity

/**
 * Created by Arasu on 11-05-2021.
 */
@Entity(tableName = "githubcomments")
data class CommentsCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "author_association")
    val author_association: String?,

    @ColumnInfo(name = "body")
    val body: String?,

    @ColumnInfo(name = "created_at")
    val created_at: String?,

    @ColumnInfo(name = "html_url")
    val html_url: String?,

    @ColumnInfo(name = "issue_url")
    val issue_url: String?,

    @ColumnInfo(name = "node_id")
    val node_id: String?,

    @ColumnInfo(name = "performed_via_github_app")
    val performed_via_github_app: String?,

    @ColumnInfo(name = "updated_at")
    val updated_at: String?,

    @ColumnInfo(name = "url")
    val url: String?,

    @ColumnInfo(name = "user")
    val user: String?
)