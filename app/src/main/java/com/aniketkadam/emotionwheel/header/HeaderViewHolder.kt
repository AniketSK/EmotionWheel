package com.aniketkadam.emotionwheel.header

import androidx.recyclerview.widget.RecyclerView
import com.aniketkadam.emotionwheel.databinding.HeaderItemBinding

class HeaderViewHolder(private val binding: HeaderItemBinding, private val onClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(header: String) {
        binding.apply {
            buttonName = header
            headerButton.setOnClickListener { onClick(adapterPosition) }
            executePendingBindings()
        }
    }
}