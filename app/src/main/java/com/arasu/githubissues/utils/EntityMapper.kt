package com.arasu.githubissues.utils


/**
 * Created by Arasu on 10-05-2021.
 */
interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity
}