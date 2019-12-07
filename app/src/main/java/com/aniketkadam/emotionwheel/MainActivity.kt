package com.aniketkadam.emotionwheel

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.aniketkadam.emotionwheel.pastemotion.EmotionJourneyFragment
import com.aniketkadam.emotionwheel.pastemotion.EmotionJourneyFragmentDirections
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import kotlin.reflect.KClass

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.emotionSelection -> switchScreen(EmotionListFragment::class)
            R.id.emotionJourneyList -> switchScreen(EmotionJourneyFragment::class)
            else -> false
        }


    private fun <T : KClass<out Fragment>> switchScreen(fragment: T): Boolean {
        with(findNavController(R.id.nav_host_fragment)) {
            when (fragment) {
                EmotionListFragment::class -> safeNavigate(EmotionJourneyFragmentDirections.actionEmotionJourneyFragmentToEmotionListFragment())
                EmotionJourneyFragment::class -> safeNavigate(EmotionListFragmentDirections.actionEmotionListFragmentToEmotionJourneyFragment())
            }
        }
        return true
    }
}

private fun NavController.safeNavigate(d: NavDirections) =
    currentDestination?.getAction(d.actionId)?.let { navigate(d) }
        ?: Timber.e("Invalid route for direction ${d} with id ${d.actionId}")
