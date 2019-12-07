package com.aniketkadam.emotionwheel.pastemotion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import com.aniketkadam.emotionwheel.R
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 */
class EmotionJourneyFragment : ListFragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @Inject
    lateinit var vm: EmotionJourneyVm

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.journey_list_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.viewState.observe(viewLifecycleOwner, Observer { renderState(it) })
    }

    fun renderState(viewState: ViewState) {
        listAdapter = getAdapter(viewState.emotionJourneys)
    }

    private fun getAdapter(emotionJourneys: List<EmotionJourneyView>) =
        ArrayAdapter<EmotionJourneyView>(
        requireActivity(),
        R.layout.emotion_journey_item,
        emotionJourneys
    )

}
