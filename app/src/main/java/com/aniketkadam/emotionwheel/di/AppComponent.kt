package com.aniketkadam.emotionwheel.di

import com.aniketkadam.emotionwheel.storage.di.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        DatabaseModule::class,
        AppModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(app: DaggerApplication)

    @Component.Factory
    interface Builder {

        fun create(@BindsInstance app: DaggerApplication): AppComponent
    }
}