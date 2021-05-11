package com.arasu.githubissues.ui.launch

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arasu.githubissues.R
import com.arasu.githubissues.ui.home.GithubIssueActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_launch.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*
import kotlin.concurrent.schedule

/**
 * Created by Arasu on 10-05-2021.
 */

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {
    private val SPLASH_DELAY_TIME: Long = 1000
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        context = this

        mSplash.setTransition(R.id.start, R.id.end)
        mSplash.transitionToEnd()

        Timer().schedule(SPLASH_DELAY_TIME)
        {
            startActivity(Intent(context, GithubIssueActivity::class.java))
            finish()
        }
    }
}