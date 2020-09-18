package com.cavista_test.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cavista_test.R
import com.cavista_test.databinding.ItemSearchBinding
import com.cavista_test.details.ImageDetailsActivity
import com.cavista_test.main.data.Data

class SearchAdapter : ListAdapter<Data, SearchAdapter.ViewHolder>(SearchItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSearchBinding>(inflater, R.layout.item_search, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dataItem: Data) {

            dataItem.images?.let {
                if (it.isNotEmpty()) {
                    binding.imageItem = dataItem.images[0]
                    binding.executePendingBindings()
                }
            }

            // Image Click listener
            itemView.setOnClickListener {

                if (dataItem.images == null)
                {
                   Toast.makeText(itemView.context,"No Preview Available!",Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent(itemView.context, ImageDetailsActivity::class.java)
                    intent.putExtra("dataItem", dataItem)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    class SearchItemDiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(old: Data, aNew: Data): Boolean {
            return old.id == aNew.id
        }

        override fun areContentsTheSame(old: Data, aNew: Data): Boolean {
            return old == aNew
        }
    }
}