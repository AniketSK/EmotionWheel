package com.aniketkadam.emotionwheel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EmotionListFactory(private val repo: EmotionRepo) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(EmotionListViewModel::class.java) -> EmotionListViewModel(repo)
            else -> throw IllegalArgumentException()
        }
    } as T
}