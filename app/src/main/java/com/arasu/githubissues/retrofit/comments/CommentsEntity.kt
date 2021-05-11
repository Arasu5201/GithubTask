package com.arasu.githubissues.retrofit.comments

import com.arasu.githubissues.retrofit.UserEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Arasu on 11-05-2021.
 */
data class CommentsEntity(
    @SerializedName("author_association")
    @Expose
    val author_association: String?,
    @SerializedName("body")
    @Expose
    val body: String?,
    @SerializedName("created_at")
    @Expose
    val created_at: String?,
    @SerializedName("html_url")
    @Expose
    val html_url: String?,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("issue_url")
    @Expose
    val issue_url: String?,
    @SerializedName("node_id")
    @Expose
    val node_id: String?,
    @SerializedName("performed_via_github_app")
    @Expose
    val performed_via_github_app: String?,
    @SerializedName("updated_at")
    @Expose
    val updated_at: String?,
    @SerializedName("url")
    @Expose
    val url: String?,
    @SerializedName("user")
    @Expose
    val user: UserEntity?
)
