package com.arasu.githubissues.di

import android.content.Context
import androidx.room.Room
import com.arasu.githubissues.room.GitDao
import com.arasu.githubissues.room.GitDatabase
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
object RoomModule {

    @Singleton
    @Provides
    fun provideGithubDb(@ApplicationContext context: Context): GitDatabase {
        return Room.databaseBuilder(context, GitDatabase::class.java, GitDatabase.DATA_BASE)
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideGithubDao(gitDatabase: GitDatabase): GitDao {
        return gitDatabase.gitDao()
    }
}