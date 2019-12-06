package com.aniketkadam.emotionwheel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * When you have one source of truth livedata, usually your view state
 * and other livedata which react to changes in that livedata,
 * the lifecyle binder ensures they all start up together and end together.
 */
class LifecycleBinder<T>(source: LiveData<T>, vararg sideEffects: LiveData<*>) :
    MediatorLiveData<T>() {
    init {
        addSource(source) { value = it }
        sideEffects.forEach {
            addSource(it) {}
        }
    }
}
