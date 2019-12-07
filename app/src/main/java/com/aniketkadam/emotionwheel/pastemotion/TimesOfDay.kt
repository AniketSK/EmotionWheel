package com.aniketkadam.emotionwheel.pastemotion

import androidx.annotation.StringRes
import com.aniketkadam.emotionwheel.R

enum class TimesOfDay(@StringRes val displayNameStringId: Int) {
    DEEP_NIGHT(R.string.deep_night), MORNING(
        R.string.morning
    ),
    AFTERNOON(R.string.afternoon), EVENING(
        R.string.evening
    ),
    NIGHT(R.string.night), UNKNOWN(
        R.string.unknown_time
    )
}