package com.aniketkadam.emotionwheel.di

import com.aniketkadam.emotionwheel.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, FragmentBuilderModule::class])
    abstract fun bindActivities(): MainActivity

}