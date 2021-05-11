package com.arasu.githubissues.retrofit.issues

import com.arasu.githubissues.retrofit.UserEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Arasu on 10-05-2021.
 */
data class GitModelItemEntity(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("active_lock_reason")
    @Expose
    var active_lock_reason: String?,
    @SerializedName("assignee")
    @Expose
    var assignee: String?,
    @SerializedName("author_association")
    @Expose
    var author_association: String?,
    @SerializedName("body")
    @Expose
    var body: String?,
    @SerializedName("closed_at")
    @Expose
    var closed_at: String?,
    @SerializedName("comments")
    @Expose
    var comments: Int,
    @SerializedName("comments_url")
    @Expose
    var comments_url: String?,
    @SerializedName("created_at")
    @Expose
    var created_at: String?,
    @SerializedName("events_url")
    @Expose
    var events_url: String?,
    @SerializedName("html_url")
    @Expose
    var html_url: String?,
    @SerializedName("labels_url")
    @Expose
    var labels_url: String?,
    @SerializedName("locked")
    @Expose
    var locked: Boolean,
    @SerializedName("node_id")
    @Expose
    var node_id: String?,
    @SerializedName("number")
    @Expose
    var number: Int,
    @SerializedName("performed_via_github_app")
    @Expose
    var performed_via_github_app: String?,
    @SerializedName("repository_url")
    @Expose
    var repository_url: String?,
    @SerializedName("state")
    @Expose
    var state: String?,
    @SerializedName("title")
    @Expose
    var title: String?,
    @SerializedName("updated_at")
    @Expose
    var updated_at: String?,
    @SerializedName("url")
    @Expose
    var url: String?,
    @SerializedName("user")
    @Expose
    var user: UserEntity?
)