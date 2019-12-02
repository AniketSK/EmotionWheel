package com.aniketkadam.emotionwheel

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
    }
}
