package com.aniketkadam.emotionwheel.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aniketkadam.emotionwheel.Emotion

@Entity
data class EmotionJourney(
    val time: Long,
    val emotionPath: List<Emotion>,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)