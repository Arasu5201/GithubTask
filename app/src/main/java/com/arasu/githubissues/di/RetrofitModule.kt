package com.arasu.githubissues.di

import com.arasu.githubissues.retrofit.GitRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Arasu on 10-05-2021.
 */

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/repos/square/okhttp/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(provideInterceptor())
    }

    private fun provideInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return OkHttpClient.Builder()
            .addInterceptor(interceptor) // same for .addInterceptor(...)
            .connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideGithubService(retrofit: Retrofit.Builder): GitRetrofit {
        return retrofit
            .build()
            .create(GitRetrofit::class.java)
    }
}
