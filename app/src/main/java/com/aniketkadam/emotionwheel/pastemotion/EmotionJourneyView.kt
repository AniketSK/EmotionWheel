package com.aniketkadam.emotionwheel.pastemotion

import com.aniketkadam.emotionwheel.data.EmotionJourney

data class EmotionJourneyView(
    val emotionJourney: EmotionJourney,
    val textRepresentation: String
) {

    override fun toString(): String = textRepresentation
}
