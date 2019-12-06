package com.aniketkadam.emotionwheel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aniketkadam.emotionwheel.storage.EmotionDao
import javax.inject.Inject

class EmotionListFactory @Inject constructor(
    private val repo: EmotionRepo,
    private val emotionDao: EmotionDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(EmotionListViewModel::class.java) -> EmotionListViewModel(
                repo,
                emotionDao
            )
            else -> throw IllegalArgumentException()
        }
    } as T
}