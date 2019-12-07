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

    @Test
    fun `morning is calculated correctly`() {
        val morningStart = midnightJourney.copy(time = getDayWithTimeSetTo(6, 0))
        val morningEnd = midnightJourney.copy(time = getDayWithTimeSetTo(11, 59))

        assertThat(uc.getTimeOfDay(morningStart), equalTo(TimesOfDay.MORING))
        assertThat(uc.getTimeOfDay(morningEnd), equalTo(TimesOfDay.MORING))
    }

    @Test
    fun `afternoon is calculated correctly`() {
        val afternoonStart = midnightJourney.copy(time = getDayWithTimeSetTo(12, 0))
        val afternoonEnd = midnightJourney.copy(time = getDayWithTimeSetTo(17, 59))
        assertThat(uc.getTimeOfDay(afternoonStart), equalTo(TimesOfDay.AFTERNOON))
        assertThat(uc.getTimeOfDay(afternoonEnd), equalTo(TimesOfDay.AFTERNOON))
    }

    @Test
    fun `evening is calculated correctly`() {
        val eveningStart = midnightJourney.copy(time = getDayWithTimeSetTo(18, 0))
        val eveningEnd = midnightJourney.copy(time = getDayWithTimeSetTo(20, 59))

        assertThat(uc.getTimeOfDay(eveningStart), equalTo(TimesOfDay.EVENING))
        assertThat(uc.getTimeOfDay(eveningEnd), equalTo(TimesOfDay.EVENING))
    }

    @Test
    fun `night is calculated correctly`() {
        val nightStart = midnightJourney.copy(time = getDayWithTimeSetTo(21, 0))
        val nightEnd = midnightJourney.copy(time = getDayWithTimeSetTo(23, 59))

        assertThat(uc.getTimeOfDay(nightStart), equalTo(TimesOfDay.NIGHT))
        assertThat(uc.getTimeOfDay(nightEnd), equalTo(TimesOfDay.NIGHT))
    }

    @Test
    fun `last moment of night is calculated correctly`() {
        val lastMomentsOfNight =
            midnightJourney.copy(time = DateTime(2019, 12, 7, 23, 59, 59, 1).millis)
        val lastMomentsOfNight2 =
            midnightJourney.copy(time = DateTime(2019, 12, 7, 23, 59, 59, 999).millis)
        assertThat(uc.getTimeOfDay(lastMomentsOfNight), equalTo(TimesOfDay.NIGHT))
        assertThat(uc.getTimeOfDay(lastMomentsOfNight2), equalTo(TimesOfDay.NIGHT))
    }

    private fun getDayWithTimeSetTo(hour: Int, minutes: Int = 0) =
        DateTime(2019, 12, 7, hour, minutes).millis
}