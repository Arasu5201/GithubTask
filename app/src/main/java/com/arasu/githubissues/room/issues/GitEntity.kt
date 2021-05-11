package com.arasu.githubissues.room.issues

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Arasu on 10-05-2021.
 */
@Entity(tableName = "githubissues")
data class GitEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "active_lock_reason")
    var active_lock_reason: String?,

    @ColumnInfo(name = "assignee")
    var assignee: String?,

    @ColumnInfo(name = "author_association")
    var author_association: String?,

    @ColumnInfo(name = "body")
    var body: String?,

    @ColumnInfo(name = "closed_at")
    var closed_at: String?,

    @ColumnInfo(name = "comments")
    var comments: Int,

    @ColumnInfo(name = "comments_url")
    var comments_url: String?,

    @ColumnInfo(name = "created_at")
    var created_at: String?,

    @ColumnInfo(name = "events_url")
    var events_url: String?,

    @ColumnInfo(name = "html_url")
    var html_url: String?,

    @ColumnInfo(name = "labels_url")
    var labels_url: String?,

    @ColumnInfo(name = "locked")
    var locked: Boolean,

    @ColumnInfo(name = "node_id")
    var node_id: String?,

    @ColumnInfo(name = "number")
    var number: Int,

    @ColumnInfo(name = "performed_via_github_app")
    var performed_via_github_app: String?,

    @ColumnInfo(name = "repository_url")
    var repository_url: String?,

    @ColumnInfo(name = "state")
    var state: String?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "updated_at")
    var updated_at: String?,

    @ColumnInfo(name = "url")
    var url: String?,

    @ColumnInfo(name = "user")
    var user: String?
)
