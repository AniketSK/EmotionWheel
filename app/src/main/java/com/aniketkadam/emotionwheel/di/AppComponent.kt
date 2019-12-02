package com.aniketkadam.emotionwheel.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(app: DaggerApplication)

    @Component.Factory
    interface Builder {

        fun create(@BindsInstance app: DaggerApplication): AppComponent
    }
}