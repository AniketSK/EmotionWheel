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
    val midnightJourney = EmotionJourney(getDayWithTimeSetTo(0), emptyList())

    @Before
    fun setup() {
        uc = EmotionJourneyToViewUseCase()
    }

    @Test
    fun `deep night is calculated correctly`() {
        assertThat(uc.getTimeOfDay(midnightJourney), equalTo(TimesOfDay.DEEP_NIGHT))
        assertThat(
            uc.getTimeOfDay(midnightJourney.copy(time = getDayWithTimeSetTo(5, 59))),
            equalTo(TimesOfDay.DEEP_NIGHT)
        )
    }

    private fun getDayWithTimeSetTo(hour: Int, minutes: Int = 0) =
        DateTime(2019, 12, 7, hour, minutes).millis
}