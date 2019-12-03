package com.aniketkadam.emotionwheel.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.support.DaggerApplication

@Module
abstract class AppModule {
    @Binds
    abstract fun bindApplication(app: DaggerApplication): Application

    @Binds
    abstract fun bindAppContext(app: DaggerApplication): Context
}