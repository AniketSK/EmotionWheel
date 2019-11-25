package com.aniketkadam.emotionwheel

import android.app.Application
import com.google.gson.Gson

class EmotionRepo(val application: Application) {

    val allEmotionList: Emotion by lazy {
        application.assets.open("emotion_db.json").bufferedReader().use { it.readText() }.let {
            Gson().fromJson(
                it,
                Emotion::class.java
            )
        }
    }
}