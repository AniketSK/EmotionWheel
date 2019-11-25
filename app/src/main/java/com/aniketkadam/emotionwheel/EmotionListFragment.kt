package com.aniketkadam.emotionwheel

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class EmotionListFragment : ListFragment() {

    lateinit var vm: EmotionListViewModel

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
        listAdapter = getAdapter(state.emotionList)
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