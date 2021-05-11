package com.arasu.githubissues.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arasu.githubissues.model.GitModelItem
import com.arasu.githubissues.repository.IssuesRepository
import com.arasu.githubissues.utils.MainStateEven
import com.arasu.githubissues.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Arasu on 10-05-2021.
 */

@HiltViewModel
class GithubIssueViewModel @Inject constructor(
    private val issuesRepository: IssuesRepository
) : ViewModel() {
    private val _githubData: MutableLiveData<Resource<List<GitModelItem>>> = MutableLiveData()
    val githubData: LiveData<Resource<List<GitModelItem>>>
        get() = _githubData

    fun setStateGithub(mainStateEvent: MainStateEven) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEven.GetGithubDatas -> {
                    issuesRepository.getGitHupUsers()
                        .onEach { data ->
                            _githubData.value = data
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEven.None -> {
                    // To-Do
                }
            }
        }
    }
}