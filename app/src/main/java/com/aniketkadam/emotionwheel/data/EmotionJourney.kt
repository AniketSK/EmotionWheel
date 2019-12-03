package com.aniketkadam.emotionwheel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmotionJourney(
    val time: Long,
    val emotionPath: List<String>,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)