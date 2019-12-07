package com.aniketkadam.emotionwheel.pastemotion

import androidx.annotation.VisibleForTesting
import com.aniketkadam.emotionwheel.data.EmotionJourney
import org.joda.time.DateTime
import org.joda.time.Instant
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat

class EmotionJourneyToViewUseCase {

    private val formatter = DateTimeFormat.forPattern("d MMMM")

    // Note, while we use localtime, all fields may be queried so there may be edge cases where this fails if the day matters.
    val localTime: LocalTime = DateTime().withTimeAtStartOfDay().toLocalTime()
    val midnight = localTime.withHourOfDay(0)
    val morningStart = localTime.withHourOfDay(6)
    val morningEnd = localTime.withHourOfDay(12)
    val afternoonStart = localTime.withHourOfDay(12)
    val afternoonEnd = localTime.withHourOfDay(18)
    val eveningStart = localTime.withHourOfDay(18)
    val nightStart = localTime.withHourOfDay(21)


    private fun getPathRepresentation(journey: EmotionJourney): String =
        journey.emotionPath.joinToString(" -> ")

    private fun getDateRepresentation(journey: EmotionJourney): String {
        return formatter.print(Instant(journey.time))
    }

    fun journeyToView(
        journey: EmotionJourney,
        stringMapper: TranslationStringMapper
    ): EmotionJourneyView =
        EmotionJourneyView(journey, getTextRepresentation(journey, stringMapper))

    @VisibleForTesting
    fun getTextRepresentation(journey: EmotionJourney, stringMapper: TranslationStringMapper) =
        "${stringMapper.getStringForId(getTimeOfDay(journey).displayNameStringId)}\n:\n${getDateRepresentation(
            journey
        )}:\n${getPathRepresentation(journey)}"

    @VisibleForTesting
    fun getTimeOfDay(journey: EmotionJourney): TimesOfDay {

        val journalEntry = LocalTime(journey.time)

        with(journalEntry) {
            return if (isWithinInterval(midnight, morningStart))
                TimesOfDay.DEEP_NIGHT
            else if (isWithinInterval(morningStart, morningEnd))
                TimesOfDay.MORNING
            else if (isWithinInterval(afternoonStart, afternoonEnd))
                TimesOfDay.AFTERNOON
            else if (isWithinInterval(eveningStart, nightStart))
                TimesOfDay.EVENING
            else if (isEqual(nightStart) || isAfter(nightStart)) // Night is a special because there can be no time, excluding which, it can be said to be before.
                TimesOfDay.NIGHT
            else TimesOfDay.UNKNOWN
        }

    }

    private fun LocalTime.isWithinInterval(startInclusive: LocalTime, endExclusive: LocalTime) =
        isEqual(startInclusive) || (isAfter(startInclusive) && isBefore(endExclusive))


}

