package com.cavista_test.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cavista_test.R
import com.cavista_test.database.Comment
import com.cavista_test.databinding.ItemCommentsBinding

class CommentsAdapter : ListAdapter<Comment, CommentsAdapter.ViewHolder>(SearchItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCommentsBinding>(inflater, R.layout.item_comments, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemCommentsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(commentItem: Comment) {
            commentItem.let {
                binding.commentItem = commentItem
                binding.executePendingBindings()
            }
        }
    }

    class SearchItemDiffCallback : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(old: Comment, aNew: Comment): Boolean {
            return old.id == aNew.id
        }

        override fun areContentsTheSame(old: Comment, aNew: Comment): Boolean {
            return old == aNew
        }
    }
}