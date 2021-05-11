package com.arasu.githubissues.room.comments

import com.arasu.githubissues.model.Comments
import com.arasu.githubissues.utils.EntityMapper
import com.arasu.githubissues.utils.getObjectFromString
import com.arasu.githubissues.utils.stringFromObject
import javax.inject.Inject

/**
 * Created by Arasu on 11-05-2021.
 */
class CommentsCacheMapper @Inject constructor() : EntityMapper<CommentsCacheEntity, Comments> {
    override fun mapFromEntity(entity: CommentsCacheEntity): Comments {
        return Comments(
            author_association = entity.author_association,
            body = entity.body,
            created_at = entity.created_at,
            html_url = entity.html_url,
            id = entity.id,
            issue_url = entity.issue_url,
            node_id = entity.node_id,
            performed_via_github_app = entity.performed_via_github_app,
            updated_at = entity.updated_at,
            url = entity.url,
            user = getObjectFromString(entity.user)
        )
    }

    override fun mapToEntity(domainModel: Comments): CommentsCacheEntity {
        return CommentsCacheEntity(
            author_association = domainModel.author_association,
            body = domainModel.body,
            created_at = domainModel.created_at,
            html_url = domainModel.html_url,
            id = domainModel.id,
            issue_url = domainModel.issue_url,
            node_id = domainModel.node_id,
            performed_via_github_app = domainModel.performed_via_github_app,
            updated_at = domainModel.updated_at,
            url = domainModel.url,
            user = stringFromObject(domainModel.user)
        )
    }

    fun mapFromCommentsList(comments: List<CommentsCacheEntity>): List<Comments> {
        return comments.map { mapFromEntity(it) }
    }
}