package com.aniketkadam.emotionwheel

import com.aniketkadam.emotionwheel.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class EmotionWheelApplication : DaggerApplication() {

    private val injector by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = injector

}