package com.aniketkadam.emotionwheel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class EmotionListViewModel(repo: IEmotionRepo) : ViewModel() {

    private val navigationStack: Stack<Emotion> = Stack()

    private val _viewState = MutableLiveData<ViewState>()

    val viewState: LiveData<ViewState>
        get() = _viewState

    init {
        _viewState.postValue(ViewState(repo.allEmotionList))
    }

    fun onListItemClicked(clickedEmotion: Emotion?) {
        clickedEmotion?.let {
            navigationStack.push(viewState.value!!.currentEmotion)
            _viewState.postValue(ViewState(it))
        }
    }

    fun backPressed() {
        if (!navigationStack.isEmpty()) {
            _viewState.postValue(ViewState(navigationStack.pop()))
        }
    }

}

data class ViewState(
    val currentEmotion: Emotion
)