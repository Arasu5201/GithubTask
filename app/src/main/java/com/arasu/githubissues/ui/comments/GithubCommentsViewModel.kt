package com.arasu.githubissues.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arasu.githubissues.model.Comments
import com.arasu.githubissues.repository.CommentsRepository
import com.arasu.githubissues.utils.MainStateEven
import com.arasu.githubissues.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Arasu on 11-05-2021.
 */
@HiltViewModel
class GithubCommentsViewModel @Inject constructor(
    private val repository: CommentsRepository
) : ViewModel() {
    private val _commentsData: MutableLiveData<Resource<List<Comments>>> = MutableLiveData()
    val commentsData: LiveData<Resource<List<Comments>>>
        get() = _commentsData

    fun setStateGitComments(url: String, number: String, mainStateEven: MainStateEven) {
        viewModelScope.launch {
            when (mainStateEven) {
                is MainStateEven.GetGithubDatas -> {
                    repository.getGitHupComments(url, number)
                        .onEach { data ->
                            _commentsData.value = data
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