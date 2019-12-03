package com.aniketkadam.emotionwheel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aniketkadam.emotionwheel.storage.EmotionDao
import com.google.gson.Gson
import com.jraska.livedata.test
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
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

    @MockK
    lateinit var emotionDao: EmotionDao

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        vm = EmotionListViewModel(repo, emotionDao)
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

    @Test
    fun `clicking further into the emotions then clicking the second header item, displays correctly`() {
        // click surprised, startled, shocked,
        // on the header list, click surprised
        repeat(3) { vm.onListItemClicked(vm.viewState.value!!.currentEmotion.subEmotions[0]) }

        vm.headerIndexClicked(1)

        // check
        // header list
        // displayed emotions!

        val expectedHeaderList = listOf("all", "Surprised")

        vm.viewState.test().assertValue {
            System.out.println(it.currentEmotion)
            it.currentEmotion.name == "Surprised"
        }

        vm.viewState.test().assertValue {
            System.out.println(it.headerList)
            it.headerList == expectedHeaderList
        }

    }

    @Test
    fun `when the first header item is clicked, it returns to the list of all of them`() {
        repeat(3) { vm.onListItemClicked(vm.viewState.value!!.currentEmotion.subEmotions[1]) }
        vm.viewState.test().assertValue { it.currentEmotion.name == "Joyful" }

        vm.headerIndexClicked(0)

        vm.viewState.test().assertValue {
            System.out.println("list contains: ${it.headerList}")
            it.headerList == listOf("all")
        }

        vm.viewState.test().assertValue {
            System.out.print(it.currentEmotion)
            it.currentEmotion.name == "all"
        }
    }

    @Test
    fun `when the first header item is clicked while on the list of all items, nothing happens`() {
        vm.headerIndexClicked(0)

        vm.viewState.test().assertValue {
            System.out.println("list contains: ${it.headerList}")
            it.headerList == listOf("all")
        }

        vm.viewState.test().assertValue {
            System.out.print(it.currentEmotion)
            it.currentEmotion.name == "all"
        }

    }

}