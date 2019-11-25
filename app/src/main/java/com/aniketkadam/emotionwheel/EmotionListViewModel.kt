package com.aniketkadam.emotionwheel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
        clickedEmotion?.subEmotions?.let {

            if (it.isNotEmpty()) { // There's more list to show
                _viewState.postValue(ViewState(it))
            } else // Final emotion
            {
                _viewState.postValue(ViewState(listOf(clickedEmotion)))
            }
        }
    }

}

data class ViewState(
    val emotionList: List<Emotion>
)