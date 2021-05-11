package com.arasu.githubissues.ui.comments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arasu.githubissues.R
import com.arasu.githubissues.model.Comments
import com.arasu.githubissues.utils.MainStateEven
import com.arasu.githubissues.utils.Resource
import com.arasu.githubissues.utils.action
import com.arasu.githubissues.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class GithubCommentsActivity : AppCompatActivity() {
    private val TAG = "CommentsActivity"
    private val viewModelGithub: GithubCommentsViewModel by viewModels()
    private lateinit var adapterGithub: GithubCommentsAdapter
    private lateinit var number: String
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        supportActionBar?.title = getString(R.string.github_comments)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        number = intent.getStringExtra("number").toString()
        url = intent.getStringExtra("url").toString()

        setupUI()
        subscribeObserver()
        viewModelGithub.setStateGitComments(url, number, MainStateEven.GetGithubDatas)
        swipeUp()
    }

    private fun swipeUp() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModelGithub.setStateGitComments(url, number, MainStateEven.GetGithubDatas)
        }
    }

    private fun subscribeObserver() {
        viewModelGithub.commentsData.observe(this, Observer { commdata ->
            when (commdata) {
                is Resource.Sucess<List<Comments>> -> {
                    displayLoading(false)
                    createRecyclerView(commdata.data)
                }
                is Resource.Loading -> {
                    displayLoading(true)
                }
                is Resource.Error -> {
                    displayLoading(false)
                    displayError(commdata.exception.message)
                }
            }
        })
    }

    private fun setupUI() {
        adapterGithub = GithubCommentsAdapter()
        comments_recyclerview.layoutManager = LinearLayoutManager(this)
        comments_recyclerview.addItemDecoration(
            DividerItemDecoration(
                comments_recyclerview.context,
                (comments_recyclerview.layoutManager as LinearLayoutManager).orientation
            )
        )
        comments_recyclerview.adapter = adapterGithub
    }

    private fun displayError(message: String?) {
        if (message != null) {
            comments_recyclerview.showSnackbar(R.string.no_internet_con) {
                action(R.string.dismiss) {
                    Timber.d(TAG, getString(R.string.dismiss))
                }
            }
//            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Unknown error", Toast.LENGTH_LONG).show()
        }
    }

    private fun createRecyclerView(data: List<Comments>) {
        if (!data.isNullOrEmpty()) {
            adapterGithub.setItems(ArrayList(data))
            text_no_comments.visibility = View.GONE
            comments_recyclerview.visibility = View.VISIBLE
        } else {
            comments_recyclerview.visibility = View.GONE
            text_no_comments.visibility = View.VISIBLE
        }
    }

    private fun displayLoading(isLoading: Boolean) {
        swipeRefreshLayout.isRefreshing = isLoading
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}