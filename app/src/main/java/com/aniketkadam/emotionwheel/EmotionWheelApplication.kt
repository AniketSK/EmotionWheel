package com.aniketkadam.emotionwheel

import com.aniketkadam.emotionwheel.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

class EmotionWheelApplication : DaggerApplication() {

    private val injector by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = injector

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        JodaTimeAndroid.init(this)
        super.onCreate()
    }
}