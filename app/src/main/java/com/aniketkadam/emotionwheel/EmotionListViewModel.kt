package com.aniketkadam.emotionwheel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class EmotionListViewModel(repo: IEmotionRepo) : ViewModel() {

    private val navigationStack: Stack<Emotion> = Stack()

    private val _currentEmotion = MutableLiveData<Emotion>()

    val viewState: LiveData<ViewState> = Transformations.map(_currentEmotion) {
        ViewState(it, navigationStack.map { it.name }.plus(listOf(it.name)))
    }

    init {
        _currentEmotion.postValue(repo.allEmotionList)
    }

    fun onListItemClicked(clickedEmotion: Emotion?) {
        clickedEmotion?.let {
            navigationStack.push(viewState.value!!.currentEmotion)
            _currentEmotion.postValue(it)
        }
    }

    fun backPressed() {
        if (!navigationStack.isEmpty()) {
            _currentEmotion.postValue(navigationStack.pop())
        }
    }

    fun headerIndexClicked(position: Int) {
        if (position != 0) {
            repeat(navigationStack.size - position - 1) { navigationStack.pop() }
            _currentEmotion.postValue(navigationStack.pop())
        }
    }
}

data class ViewState(
    val currentEmotion: Emotion,
    val headerList: List<String>
)