package com.aniketkadam.emotionwheel.pastemotion

import androidx.annotation.StringRes

interface TranslationStringMapper {
    fun getStringForId(@StringRes id: Int): String
}