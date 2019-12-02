package com.aniketkadam.emotionwheel

import android.app.Application
import com.google.gson.Gson
import javax.inject.Inject

class EmotionRepo @Inject constructor(val application: Application) : IEmotionRepo {

    override val allEmotionList: Emotion by lazy {
        application.assets.open("emotion_db.json").bufferedReader().use { it.readText() }.let {
            Gson().fromJson(
                it,
                Emotion::class.java
            )
        }
    }
}