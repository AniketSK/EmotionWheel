package com.aniketkadam.emotionwheel.pastemotion

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aniketkadam.emotionwheel.data.EmotionJourney
import com.aniketkadam.emotionwheel.storage.EmotionDao
import org.joda.time.Instant
import org.joda.time.format.DateTimeFormat

class EmotionJourneyVm(emotionDao: EmotionDao) : ViewModel() {


    val viewState =
        Transformations.map(emotionDao.getAllJourneys()) { ViewState(it.map(::journeyToView)) }

    private val formatter = DateTimeFormat.forPattern("d MMMM")

    private fun journeyToView(journey: EmotionJourney): EmotionJourneyView = EmotionJourneyView(
        journey,
        "${getDateRepresentation(journey)}:\n${getPathRepresentation(journey)}"
    )

    private fun getPathRepresentation(journey: EmotionJourney): String =
        journey.emotionPath.joinToString(" -> ")

    private fun getDateRepresentation(journey: EmotionJourney): String {
        return formatter.print(Instant(journey.time))
    }

}

data class ViewState(
    val emotionJourneys: List<EmotionJourneyView>
)

