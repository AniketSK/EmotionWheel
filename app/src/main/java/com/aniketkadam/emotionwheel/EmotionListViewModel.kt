package com.aniketkadam.emotionwheel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aniketkadam.emotionwheel.data.EmotionJourney
import com.aniketkadam.emotionwheel.storage.EmotionDao
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*

class EmotionListViewModel(repo: IEmotionRepo, emotionDao: EmotionDao) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val navigationStack: Stack<Emotion> = Stack()

    private val _currentEmotion = MutableLiveData<Emotion>()

    private val _viewState: LiveData<ViewState> = Transformations.map(_currentEmotion) {
        ViewState(it, navigationStack.map { it.name }.plus(listOf(it.name)))
    }

    private val _saveEmotionWhenEmpty = Transformations.map(_viewState) {
        it.takeIf { it.currentEmotion.subEmotions.isEmpty() }?.let {
            Timber.d("Saving data")
            disposable.add(
                emotionDao.insert(
                    EmotionJourney(
                        System.currentTimeMillis(),
                        it.headerList
                    )
                ).subscribeOn(Schedulers.io()).subscribe()
            )
        }
    }

    val viewState: LiveData<ViewState> = LifecycleBinder(_viewState, _saveEmotionWhenEmpty)

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
        } else if (!navigationStack.empty()) {
            val firstElement = navigationStack.firstElement()
            navigationStack.clear()
            _currentEmotion.postValue(firstElement)
        }
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}

data class ViewState(
    val currentEmotion: Emotion,
    val headerList: List<String>
)