package com.cavista_test.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cavista_test.R
import com.cavista_test.databinding.ItemDetailsBinding
import com.cavista_test.main.data.Image

class ImageAdapter : ListAdapter<Image, ImageAdapter.ViewHolder>(SearchItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemDetailsBinding>(inflater, R.layout.item_details, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemDetailsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dataItem: Image) {

            dataItem.let {
                binding.imageItem = dataItem
                binding.executePendingBindings()
            }
        }
    }

    class SearchItemDiffCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(old: Image, aNew: Image): Boolean {
            return old.id == aNew.id
        }

        override fun areContentsTheSame(old: Image, aNew: Image): Boolean {
            return old == aNew
        }
    }
}