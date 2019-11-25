package com.aniketkadam.emotionwheel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class EmotionListViewModel(repo: EmotionRepo) : ViewModel() {

    private val _viewState by lazy {
        MutableLiveData<ViewState>().apply { ViewState(emptyList()) }
    }

    val viewState: LiveData<ViewState>
        get() = _viewState

    init {
        _viewState.postValue(ViewState(repo.allEmotionList.subEmotions))
    }

    fun onListItemClicked(clickedEmotion: Emotion?) {
        Timber.d("item clicked ${clickedEmotion}")
    }

}

data class ViewState(
    val emotionList: List<Emotion>
)