package com.aniketkadam.emotionwheel.header

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aniketkadam.emotionwheel.R
import com.aniketkadam.emotionwheel.databinding.HeaderItemBinding

class HeaderListAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<String, HeaderViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = DataBindingUtil.inflate<HeaderItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.header_item,
            parent,
            false
        )
        return HeaderViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

        }
    }

}