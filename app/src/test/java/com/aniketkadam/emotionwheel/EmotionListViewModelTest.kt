package com.aniketkadam.emotionwheel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.jraska.livedata.test
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmotionListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var vm: EmotionListViewModel
    val repo: IEmotionRepo = object : IEmotionRepo {
        override val allEmotionList: Emotion
            get() = getTextInFile("emotion_db.json").let {
                Gson().fromJson(
                    it,
                    Emotion::class.java
                )
            }

    }

    @Before
    fun setup() {
        vm = EmotionListViewModel(repo)
        vm.viewState.test() // Required for the livedata to be inited.
    }

    @Test
    fun `vm is initialized with the correct value`() {
        vm.viewState.test()
            .assertValue { it.currentEmotion.name == "all" && it.currentEmotion.subEmotions.size == 7 }
    }

    @Test
    fun `when an item is clicked it becomes the currently selected emotion`() {
        val clickedItem = vm.viewState.value!!.currentEmotion.subEmotions[1]
        vm.onListItemClicked(clickedItem)
        vm.viewState.test().assertValue { it.currentEmotion == clickedItem }
    }

    @Test
    fun `when an item is clicked and then back is pressed, it goes back to the initial state`() {
        val initalValue = vm.viewState.value!!.currentEmotion
        val clickedItem = initalValue.subEmotions[1]
        vm.onListItemClicked(clickedItem)
        vm.viewState.test().assertValue { it.currentEmotion == clickedItem }
        vm.backPressed()
        vm.viewState.test().assertValue { it.currentEmotion == initalValue }
    }

    @Test
    fun `when a header item is clicked, it switches to that emotion`() {
        repeat(2) { vm.onListItemClicked(vm.viewState.value!!.currentEmotion.subEmotions[1]) }
        vm.viewState.test().assertValue { it.currentEmotion.name == "Content" }

        vm.headerIndexClicked(1)

        vm.viewState.test().assertValue { it.currentEmotion.name == "Happy" }
    }

    @Test
    fun `when a header item is clicked, the header shows the appropriate list`() {
        repeat(2) { vm.onListItemClicked(vm.viewState.value!!.currentEmotion.subEmotions[1]) }
        vm.viewState.test().assertValue { it.currentEmotion.name == "Content" }

        vm.headerIndexClicked(1)

        vm.viewState.test().assertValue {
            System.out.println("list contains: ${it.headerList}")
            it.headerList == listOf("all", "Happy")
        }
    }
}