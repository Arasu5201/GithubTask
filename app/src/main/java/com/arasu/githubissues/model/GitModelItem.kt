package com.arasu.githubissues.model

import com.arasu.githubissues.retrofit.UserEntity

data class GitModelItem(
    val active_lock_reason: String?,
    val assignee: String?,
    val author_association: String?,
    val body: String?,
    val closed_at: String?,
    val comments: Int,
    val comments_url: String?,
    val created_at: String?,
    val events_url: String?,
    val html_url: String?,
    val id: Int,
    val labels_url: String?,
    val locked: Boolean,
    val node_id: String?,
    val number: Int,
    val performed_via_github_app: String?,
    val repository_url: String?,
    val state: String?,
    val title: String?,
    val updated_at: String?,
    val url: String?,
    val user: UserEntity?
)