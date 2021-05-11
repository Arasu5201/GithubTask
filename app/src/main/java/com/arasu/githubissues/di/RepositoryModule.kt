package com.arasu.githubissues.di

import android.content.Context
import com.arasu.githubissues.repository.CommentsRepository
import com.arasu.githubissues.repository.IssuesRepository
import com.arasu.githubissues.retrofit.GitRetrofit
import com.arasu.githubissues.retrofit.comments.CommentsMapper
import com.arasu.githubissues.retrofit.issues.NetworkMapper
import com.arasu.githubissues.room.GitDao
import com.arasu.githubissues.room.comments.CommentsCacheMapper
import com.arasu.githubissues.room.issues.IssueCacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Arasu on 10-05-2021.
 */

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun getGithubRepository(
        @ApplicationContext context: Context,
        gitDao: GitDao,
        gitRetrofit: GitRetrofit,
        networkMapper: NetworkMapper,
        issueCacheMapper: IssueCacheMapper
    ): IssuesRepository {
        return IssuesRepository(context, gitDao, gitRetrofit, networkMapper, issueCacheMapper)
    }

    @Singleton
    @Provides
    fun getGithubCommRepository(
        @ApplicationContext context: Context,
        gitDao: GitDao,
        gitRetrofit: GitRetrofit,
        commentsMapper: CommentsMapper,
        commentsCacheMapper: CommentsCacheMapper
    ): CommentsRepository {
        return CommentsRepository(context, gitDao, gitRetrofit, commentsMapper, commentsCacheMapper)
    }
}