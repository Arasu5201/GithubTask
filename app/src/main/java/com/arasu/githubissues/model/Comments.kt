package com.arasu.githubissues.model

import com.arasu.githubissues.retrofit.UserEntity

data class Comments(
    var author_association: String?,
    var body: String?,
    var created_at: String?,
    var html_url: String?,
    var id: Int,
    var issue_url: String?,
    var node_id: String?,
    var performed_via_github_app: String?,
    var updated_at: String?,
    var url: String?,
    var user: UserEntity?
)