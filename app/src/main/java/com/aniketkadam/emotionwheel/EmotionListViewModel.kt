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
        _viewState.postValue(ViewState(repo.allEmotionList, listOf("All")))
    }

    fun onListItemClicked(clickedEmotion: Emotion?) {
        clickedEmotion?.let {
            navigationStack.push(viewState.value!!.currentEmotion)
            _viewState.postValue(ViewState(it, getCurrentHeaderList().plus(it.name)))
        }
    }

    private fun getCurrentHeaderList() =
        _viewState.value!!.headerList


    fun backPressed() {
        if (!navigationStack.isEmpty()) {
            _viewState.postValue(
                ViewState(
                    navigationStack.pop(),
                    getCurrentHeaderList().dropLast(1)
                )
            )
        }
    }

    fun headerIndexClicked(position: Int) {
        if (position != 0) {
            repeat(position - 1) { navigationStack.pop() }
            _viewState.postValue(
                ViewState(
                    navigationStack.pop(),
                    getCurrentHeaderList().filterIndexed { index, _ -> index <= position })
            )
        }
    }
}

data class ViewState(
    val currentEmotion: Emotion,
    val headerList: List<String>
)