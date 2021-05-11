package com.arasu.githubissues.retrofit.issues

import com.arasu.githubissues.model.GitModelItem
import com.arasu.githubissues.utils.EntityMapper
import javax.inject.Inject

/**
 * Created by Arasu on 10-05-2021.
 */
class NetworkMapper @Inject constructor() : EntityMapper<GitModelItemEntity, GitModelItem> {

    override fun mapFromEntity(entity: GitModelItemEntity): GitModelItem {
        return GitModelItem(
            active_lock_reason = entity.active_lock_reason,
            assignee = entity.assignee,
            author_association = entity.author_association,
            body = entity.body,
            closed_at = entity.closed_at,
            comments = entity.comments,
            comments_url = entity.comments_url,
            created_at = entity.created_at,
            events_url = entity.events_url,
            html_url = entity.html_url,
            id = entity.id,
            labels_url = entity.labels_url,
            locked = entity.locked,
            node_id = entity.node_id,
            number = entity.number,
            performed_via_github_app = entity.performed_via_github_app,
            repository_url = entity.repository_url,
            state = entity.state,
            title = entity.title,
            updated_at = entity.updated_at,
            url = entity.url,
            user = entity.user
        )

    }

    override fun mapToEntity(domainModel: GitModelItem): GitModelItemEntity {
        return GitModelItemEntity(
            active_lock_reason = domainModel.active_lock_reason,
            assignee = domainModel.assignee,
            author_association = domainModel.author_association,
            body = domainModel.body,
            closed_at = domainModel.closed_at,
            comments = domainModel.comments,
            comments_url = domainModel.comments_url,
            created_at = domainModel.created_at,
            events_url = domainModel.events_url,
            html_url = domainModel.html_url,
            id = domainModel.id,
            labels_url = domainModel.labels_url,
            locked = domainModel.locked,
            node_id = domainModel.node_id,
            number = domainModel.number,
            performed_via_github_app = domainModel.performed_via_github_app,
            repository_url = domainModel.repository_url,
            state = domainModel.state,
            title = domainModel.title,
            updated_at = domainModel.updated_at,
            url = domainModel.url,
            user = domainModel.user
        )
    }

    fun mapFromEntityList(entities: List<GitModelItemEntity>): List<GitModelItem> {
        return entities.map { mapFromEntity(it) }
    }
}