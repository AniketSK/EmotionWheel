package com.aniketkadam.emotionwheel.pastemotion

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aniketkadam.emotionwheel.data.EmotionJourney
import com.aniketkadam.emotionwheel.storage.EmotionDao

class EmotionJourneyVm(emotionDao: EmotionDao) : ViewModel() {

    val viewState = Transformations.map(emotionDao.getAllJourneys()) { ViewState(it) }

}

data class ViewState(
    val emotionJourneys: List<EmotionJourney>
)