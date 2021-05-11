package com.arasu.githubissues.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.arasu.githubissues.R
import com.arasu.githubissues.model.GitModelItem
import com.arasu.githubissues.utils.dateConversion
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.recyclerview_item_github.view.*

/**
 * Created by Arasu on 10-05-2021.
 */
class GithubIssueAdapter(private val listener: GitHubItemListener) :
    RecyclerView.Adapter<GithubIssueAdapter.MyViewHolder>() {

    private val items = ArrayList<GitModelItem>()

    fun setItems(items: ArrayList<GitModelItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface GitHubItemListener {
        fun onClickedBlog(url: String, number: String)
    }

    class MyViewHolder(itemView: View, private val listener: GitHubItemListener) :
        RecyclerView.ViewHolder(itemView) {
        val itemLayout: ConstraintLayout = itemView.blog_layout
        val textName: TextView = itemView.text_name
        val textDate: TextView = itemView.text_date
        val textTitle: TextView = itemView.text_title
        val textDescription: TextView = itemView.text_description
        val image: ImageView = itemView.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_github, parent, false)
        return MyViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textName.text = items[position].user?.login
        holder.textDate.text = items[position].updated_at?.let { dateConversion(it) }
        holder.textTitle.text = items[position].title
        holder.textDescription.text = items[position].body
        Glide.with(holder.itemLayout).load(items[position].user?.avatar_url)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
//        item = items[position]

        holder.itemLayout.setOnClickListener {
            listener.onClickedBlog(
                items[position].url.toString(),
                items[position].number.toString()
            )
        }
    }

    override fun getItemCount(): Int = items.size
}