package com.aniketkadam.emotionwheel.pastemotion

import android.app.Application
import android.content.res.Resources
import javax.inject.Inject

class AndroidTranslationStringMapper @Inject constructor(application: Application) :
    TranslationStringMapper {

    val res: Resources = application.resources

    override fun getStringForId(id: Int): String = res.getString(id)

}