package com.arasu.githubissues.retrofit

import com.arasu.githubissues.retrofit.comments.CommentsEntity
import com.arasu.githubissues.retrofit.issues.GitModelItemEntity
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Arasu on 10-05-2021.
 */
interface GitRetrofit {
    @GET("issues")
    suspend fun getIssues(): List<GitModelItemEntity>

    @GET("issues/{input}/comments")
    suspend fun getComments(@Path("input") input: String): List<CommentsEntity>
}