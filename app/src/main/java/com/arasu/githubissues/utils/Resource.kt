package com.arasu.githubissues.utils

import java.lang.Exception

/**
 * Created by Arasu on 10-05-2021.
 */
sealed class Resource<out R> {
    data class Sucess<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
