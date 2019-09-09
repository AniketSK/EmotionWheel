package com.aniketkadam.emotionwheel

import androidx.annotation.ColorRes

data class Emotion(
    val name: String,
    @ColorRes val color: Int,
    val subEmotions: List<Emotion>
)