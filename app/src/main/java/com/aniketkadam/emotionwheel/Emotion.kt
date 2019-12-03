package com.aniketkadam.emotionwheel

import androidx.annotation.ColorRes
import com.google.gson.annotations.SerializedName

data class Emotion(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("color") @ColorRes val color: Int,
    @field:SerializedName("subEmotions") val subEmotions: List<Emotion>
) {
    override fun toString(): String = name
}