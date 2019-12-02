package com.aniketkadam.emotionwheel.di

import com.aniketkadam.emotionwheel.EmotionListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [EmotionListFragmentModule::class])
    abstract fun provideEmotionFragment(): EmotionListFragment
}