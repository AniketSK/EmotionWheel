@file:Suppress("UNCHECKED_CAST")

package com.aniketkadam.emotionwheel.pastemotion.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aniketkadam.emotionwheel.pastemotion.EmotionJourneyVm
import com.aniketkadam.emotionwheel.pastemotion.TranslationStringMapper
import com.aniketkadam.emotionwheel.storage.EmotionDao
import javax.inject.Inject

class EmotionJourneyViewModelFactory @Inject constructor(
    private val emotionDao: EmotionDao,
    private val stringMapper: TranslationStringMapper
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(EmotionJourneyVm::class.java) -> EmotionJourneyVm(
                emotionDao,
                stringMapper
            )
            else -> throw IllegalArgumentException("$canonicalName does not know how to create the class ${modelClass.canonicalName}")
        }
    } as T

}