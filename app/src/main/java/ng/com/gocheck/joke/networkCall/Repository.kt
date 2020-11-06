package ng.com.gocheck.joke.networkCall

import ng.com.gocheck.joke.model.Jokes
import ng.com.gocheck.joke.model.db.JokeDb

class Repository (private val api : ApiRequest, private val db: JokeDb) : SafeApiRequest() {

    suspend fun getJoke() = apiRequest { api.getJokes() }

    suspend fun saveJoke(jokes: Jokes)  = db.getJokeDao().saveJoke(jokes)

    fun retrieveJoke() = db.getJokeDao().retrieveJoke()

}