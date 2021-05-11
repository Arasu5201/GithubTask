package com.arasu.githubissues.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arasu.githubissues.R
import com.arasu.githubissues.model.GitModelItem
import com.arasu.githubissues.ui.GithubIssueViewModel
import com.arasu.githubissues.ui.comments.GithubCommentsActivity
import com.arasu.githubissues.utils.MainStateEven
import com.arasu.githubissues.utils.Resource
import com.arasu.githubissues.utils.action
import com.arasu.githubissues.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


/**
 * Created by Arasu on 10-05-2021.
 */

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class GithubIssueActivity : AppCompatActivity(), GithubIssueAdapter.GitHubItemListener {

    private lateinit var adapter: GithubIssueAdapter
    private val TAG = "MainActivity"
    private val viewModel: GithubIssueViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.github_issue)

        setupUI()
        subsribeObserver()
        viewModel.setStateGithub(MainStateEven.GetGithubDatas)
        swipeup()
    }

    private fun swipeup() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.setStateGithub(MainStateEven.GetGithubDatas)
        }
    }

    private fun setupUI() {
        adapter = GithubIssueAdapter(this)
        github_recyclerview.layoutManager = LinearLayoutManager(this)
        github_recyclerview.addItemDecoration(
            DividerItemDecoration(
                github_recyclerview.context,
                (github_recyclerview.layoutManager as LinearLayoutManager).orientation
            )
        )
        github_recyclerview.adapter = adapter
    }

    private fun subsribeObserver() {
        viewModel.githubData.observe(this, Observer { datastate ->
            when (datastate) {
                is Resource.Sucess<List<GitModelItem>> -> {
                    displayLoading(false)
                    createRecyclerView(datastate.data)
                }
                is Resource.Loading -> {
                    displayLoading(true)
                }
                is Resource.Error -> {
                    displayLoading(false)
                    displayError(datastate.exception.message)
                }
            }

        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            github_recyclerview.showSnackbar(R.string.no_internet_con) {
                action(R.string.dismiss) {
                    Log.d(TAG, getString(R.string.dismiss))
                }
            }
//            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Unknown error", Toast.LENGTH_LONG).show()
        }
    }

    private fun createRecyclerView(data: List<GitModelItem>) {
        if (!data.isNullOrEmpty()) {
            adapter.setItems(ArrayList(data))
            text_no_issue.visibility = View.GONE
            github_recyclerview.visibility = View.VISIBLE
        } else {
            github_recyclerview.visibility = View.GONE
            text_no_issue.visibility = View.VISIBLE
        }
    }

    private fun displayLoading(isLoading: Boolean) {
        swipeRefreshLayout.isRefreshing = isLoading
    }

    override fun onClickedBlog(url: String, number: String) {
        val intent = Intent(this, GithubCommentsActivity::class.java)
        intent.putExtra("url", url)
        intent.putExtra("number", number)
        startActivity(intent)
    }
}
