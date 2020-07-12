package ng.com.gocheck.joke.networkCall

import ng.com.gocheck.joke.model.Jokes
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiRequest  {
// https://sv443.net/jokeapi/v2/joke/Any

    @GET("Any")
    suspend fun getJokes() : Response<Jokes>

    companion object  {

        operator fun invoke() : ApiRequest {

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://sv443.net/jokeapi/v2/joke/")
                .build()
                .create(ApiRequest::class.java)
        }
    }

}