package com.aniketkadam.emotionwheel.pastemotion

import com.aniketkadam.emotionwheel.data.EmotionJourney
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EmotionJourneyToViewUseCaseTest {

    lateinit var uc: EmotionJourneyToViewUseCase
    val midnightJourney = EmotionJourney(getDayWithHourSetTo(0).millis, emptyList())

    @Before
    fun setup() {
        uc = EmotionJourneyToViewUseCase()
    }

    @Test
    fun `time of day is calculated correctly`() {
        uc.getTimeOfDay(midnightJourney)
    }

    private fun getDayWithHourSetTo(hour: Int) = DateTime(2019, 12, 7, hour, 0)
}