package com.aniketkadam.emotionwheel.pastemotion

import com.aniketkadam.emotionwheel.data.EmotionJourney
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Config.OLDEST_SDK])
class EmotionJourneyToViewUseCaseTest {

    lateinit var uc: EmotionJourneyToViewUseCase
    val midnightJourney = EmotionJourney(getDayWithHourSetTo(0).millis, emptyList())

    @Before
    fun setup() {
        uc = EmotionJourneyToViewUseCase()
    }

    @Test
    fun `time of day is calculated correctly`() {
        assertThat(uc.getTimeOfDay(midnightJourney), equalTo(TimesOfDay.DEEP_NIGHT))
    }

    private fun getDayWithHourSetTo(hour: Int) = DateTime(2019, 12, 7, hour, 0)
}