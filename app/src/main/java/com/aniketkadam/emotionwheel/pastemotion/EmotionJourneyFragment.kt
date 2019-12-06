package com.aniketkadam.emotionwheel.pastemotion

import androidx.fragment.app.ListFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [EmotionJourneyFragment.OnListFragmentInteractionListener] interface.
 */
class EmotionJourneyFragment : ListFragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector


}
