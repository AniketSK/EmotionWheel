package com.aniketkadam.emotionwheel

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aniketkadam.emotionwheel.header.HeaderListAdapter
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.emotion_list_fragment_layout.*
import javax.inject.Inject

class EmotionListFragment : ListFragment(), HasAndroidInjector {

    @Inject
    lateinit var vm: EmotionListViewModel

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    lateinit var headerListAdapter: HeaderListAdapter

    private val backPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            vm.backPressed()
        }
    }

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        headerList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        headerListAdapter = HeaderListAdapter(vm::headerIndexClicked)
        headerList.adapter = headerListAdapter

        vm.viewState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: ViewState) {
        listAdapter = getAdapter(state.currentEmotion.subEmotions)
        empty.text = state.currentEmotion.name
        headerListAdapter.submitList(state.headerList)
    }

    private fun getAdapter(emotions: List<Emotion>) = ArrayAdapter<Emotion>(
        requireActivity(),
        R.layout.emotion_item,
        emotions
    )

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        vm.onListItemClicked(listAdapter?.getItem(position) as Emotion)
    }
}