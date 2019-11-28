package com.aniketkadam.emotionwheel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class EmotionListFragment : ListFragment() {

    private val backPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            vm.backPressed()
        }
    }

    lateinit var vm: EmotionListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.emotion_list_fragment_layout, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProviders.of(
            this,
            EmotionListFactory(EmotionRepo(requireActivity().application))
        )
            .get(EmotionListViewModel::class.java)
        vm.viewState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: ViewState) {
        listAdapter = getAdapter(state.currentEmotion.subEmotions)
    }

    private fun getAdapter(emotions: List<Emotion>) = ArrayAdapter<Emotion>(
        requireActivity(),
        android.R.layout.simple_list_item_1,
        emotions
    )

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        vm.onListItemClicked(listAdapter?.getItem(position) as Emotion)
    }
}