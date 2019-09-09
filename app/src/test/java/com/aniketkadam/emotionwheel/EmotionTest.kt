package com.aniketkadam.emotionwheel

import com.google.gson.Gson
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class EmotionTest{

    val testJson = getTextInFile("emotion_db.json").let { Gson().fromJson(it, Emotion::class.java) }

    @Test
    fun allEmotionsLoad() {
        assertThat(testJson.subEmotions.size, equalTo(7))
    }
}