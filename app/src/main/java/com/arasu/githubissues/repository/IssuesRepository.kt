package com.arasu.githubissues.repository

import android.content.Context
import android.widget.Toast
import com.arasu.githubissues.R
import com.arasu.githubissues.model.GitModelItem
import com.arasu.githubissues.retrofit.GitRetrofit
import com.arasu.githubissues.retrofit.issues.NetworkMapper
import com.arasu.githubissues.room.GitDao
import com.arasu.githubissues.room.issues.IssueCacheMapper
import com.arasu.githubissues.utils.MyException
import com.arasu.githubissues.utils.Resource
import com.arasu.githubissues.utils.isNetworkActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Arasu on 10-05-2021.
 */
class IssuesRepository constructor(
    private val context: Context,
    private val gitDao: GitDao,
    private val gitRetrofit: GitRetrofit,
    private val networkMapper: NetworkMapper,
    private val issueCacheMapper: IssueCacheMapper
) {
    suspend fun getGitHupUsers(): Flow<Resource<List<GitModelItem>>> = flow {
        emit(Resource.Loading)
        delay(1000)
        try {

            val cacheGithub = gitDao.getIssues()

            if (cacheGithub.isNotEmpty()) {
                emit(Resource.Sucess(issueCacheMapper.mapFromEntityList(cacheGithub)))
            } else {
                if (isNetworkActive(context)) {
                    val networkGitHub = gitRetrofit.getIssues()
                    val gitHubs = networkMapper.mapFromEntityList(networkGitHub)
                    for (gitHub in gitHubs) {
                        gitDao.insertIssues(issueCacheMapper.mapToEntity(gitHub))
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