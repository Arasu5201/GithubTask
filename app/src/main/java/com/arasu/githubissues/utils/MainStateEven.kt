package com.arasu.githubissues.utils

/**
 * Created by Arasu on 11-05-2021.
 */
sealed class MainStateEven {
    object GetGithubDatas : MainStateEven()
    object None : MainStateEven()
}
