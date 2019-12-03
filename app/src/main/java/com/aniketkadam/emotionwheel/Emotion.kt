package com.aniketkadam.emotionwheel

import androidx.annotation.ColorRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Emotion(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("color") @ColorRes val color: Int,
    @field:SerializedName("subEmotions") val subEmotions: List<Emotion>,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
) {
    override fun toString(): String = name
}