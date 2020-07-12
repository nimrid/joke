package ng.com.gocheck.joke.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ng.com.gocheck.joke.model.CONSTANT_ID
import ng.com.gocheck.joke.model.Jokes

@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJoke(joke : Jokes)

    @Query("SELECT * FROM jokes WHERE jokeId = $CONSTANT_ID")
    fun retrieveJoke() : LiveData<Jokes>

}