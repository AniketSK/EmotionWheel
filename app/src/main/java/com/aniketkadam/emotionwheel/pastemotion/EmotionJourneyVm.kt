package com.aniketkadam.emotionwheel.pastemotion

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aniketkadam.emotionwheel.storage.EmotionDao

class EmotionJourneyVm(emotionDao: EmotionDao) : ViewModel() {

    private val journeyViewConverter = EmotionJourneyToViewUseCase()

    val viewState =
        Transformations.map(emotionDao.getAllJourneys()) { ViewState(it.map(journeyViewConverter::journeyToView)) }

}

data class ViewState(
    val emotionJourneys: List<EmotionJourneyView>
)

