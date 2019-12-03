package com.aniketkadam.emotionwheel.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aniketkadam.emotionwheel.data.EmotionJourney
import io.reactivex.Completable

@Dao
interface EmotionDao {

    @Insert
    fun insert(emotionJourney: EmotionJourney): Completable

    @Query("SELECT * from EmotionJourney")
    fun getAllJourneys(): LiveData<List<EmotionJourney>>
}