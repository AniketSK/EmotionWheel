package com.aniketkadam.emotionwheel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import com.google.gson.Gson
import timber.log.Timber

class EmotionListFragment : ListFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        listAdapter = getAdapter(getEmotionList().subEmotions)
        return view
    }

    private fun getAdapter(emotions: List<Emotion>) = ArrayAdapter<Emotion>(
        requireActivity(),
        android.R.layout.simple_list_item_1,
        emotions
    )

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        Timber.d("item clicked ${listAdapter.getItem(position)}")
    }

    private fun getEmotionList(): Emotion =
        requireActivity().assets.open("emotion_db.json").bufferedReader().use { it.readText() }.let {
            Gson().fromJson(
                it,
                Emotion::class.java
            )
        }

}