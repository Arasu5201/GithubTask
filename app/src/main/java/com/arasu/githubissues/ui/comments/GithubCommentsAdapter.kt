package com.arasu.githubissues.ui.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.arasu.githubissues.R
import com.arasu.githubissues.model.Comments
import com.arasu.githubissues.utils.dateConversion
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.recyclerview_comments_github.view.*
import java.util.*

/**
 * Created by Arasu on 11-05-2021.
 */
class GithubCommentsAdapter() : RecyclerView.Adapter<GithubCommentsAdapter.MyHolder>() {

    private val items = ArrayList<Comments>()

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemLayout: ConstraintLayout = itemView.comm_layout
        val textName: TextView = itemView.text_comm_name
        val textDate: TextView = itemView.text_comm_date
        val textTitle: TextView = itemView.text_comm_title
        val textDescription: TextView = itemView.text_comm_description
        val image: ImageView = itemView.comm_image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_comments_github, parent, false
        )
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.textName.text = items[position].user?.login
        holder.textDate.text = items[position].updated_at?.let { dateConversion(it) }
        holder.textTitle.text = items[position].author_association
        holder.textDescription.text = items[position].body
        Glide.with(holder.itemLayout).load(items[position].user?.avatar_url)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
    }

    override fun getItemCount() = items.size

    fun setItems(arrayList: ArrayList<Comments>) {
        this.items.clear()
        this.items.addAll(arrayList)
        notifyDataSetChanged()
    }
}