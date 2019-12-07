package com.aniketkadam.emotionwheel.pastemotion

import androidx.annotation.VisibleForTesting
import com.aniketkadam.emotionwheel.data.EmotionJourney
import org.joda.time.DateTime
import org.joda.time.Hours
import org.joda.time.Instant
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat

class EmotionJourneyToViewUseCase {

    private val formatter = DateTimeFormat.forPattern("d MMMM")

    private fun getPathRepresentation(journey: EmotionJourney): String =
        journey.emotionPath.joinToString(" -> ")

    private fun getDateRepresentation(journey: EmotionJourney): String {
        return formatter.print(Instant(journey.time))
    }

    fun journeyToView(journey: EmotionJourney): EmotionJourneyView =
        EmotionJourneyView(journey, getTextRepresentation(journey))

    @VisibleForTesting
    fun getTextRepresentation(journey: EmotionJourney) =
        "${getDateRepresentation(journey)}:\n${getPathRepresentation(journey)}"

    @VisibleForTesting
    fun getTimeOfDay(journey: EmotionJourney): TimesOfDay {

        val localTime = DateTime().withTimeAtStartOfDay().toLocalTime()
        val midnight = localTime.withHourOfDay(0)
        val morningStart = localTime.withHourOfDay(6)
        val morningEnd = localTime.withHourOfDay(12)
        val eveningStart = localTime.withHourOfDay(18)
        val nightStart = localTime.withHourOfDay(9)

        val journalEntry = LocalTime(journey.time)

        with(journalEntry) {
            return if (Hours.hoursBetween(midnight, morningStart).equals(this))
                TimesOfDay.DEEP_NIGHT
            else if (isEqual(morningStart) || (isAfter(morningStart) && isBefore(morningEnd)))
                TimesOfDay.MORING
            else TimesOfDay.UNKNOWN
        }

    }

}

enum class TimesOfDay {
    DEEP_NIGHT, MORING, EVENING, NIGHT, UNKNOWN
}