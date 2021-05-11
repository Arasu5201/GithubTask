package com.arasu.githubissues.repository

import android.content.Context
import android.widget.Toast
import com.arasu.githubissues.R
import com.arasu.githubissues.model.Comments
import com.arasu.githubissues.retrofit.GitRetrofit
import com.arasu.githubissues.retrofit.comments.CommentsMapper
import com.arasu.githubissues.room.GitDao
import com.arasu.githubissues.room.comments.CommentsCacheMapper
import com.arasu.githubissues.utils.MyException
import com.arasu.githubissues.utils.Resource
import com.arasu.githubissues.utils.isNetworkActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Arasu on 10-05-2021.
 */
class CommentsRepository constructor(
    private val context: Context,
    private val gitDao: GitDao,
    private val gitRetrofit: GitRetrofit,
    private val networkMapper: CommentsMapper,
    private val issueCacheMapper: CommentsCacheMapper
) {
    suspend fun getGitHupComments(url: String, number: String): Flow<Resource<List<Comments>>> =
        flow {
            emit(Resource.Loading)
            delay(1000)
            try {
                val cacheGithub = gitDao.getComments(url)
                if (cacheGithub.isNotEmpty()) {
                    emit(Resource.Sucess(issueCacheMapper.mapFromCommentsList(cacheGithub)))
                } else {
                    if (isNetworkActive(context)) {
                        val networkGitHub = gitRetrofit.getComments(number)
                        val gitHubs = networkMapper.mapFromCommentsList(networkGitHub)
                        for (gitHub in gitHubs) {
                            gitDao.insertComments(issueCacheMapper.mapToEntity(gitHub))
                        }
                        emit(Resource.Sucess(gitHubs))
                    } else
                        emit(Resource.Error(MyException((R.string.no_internet_con).toString())))
                }

            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
}