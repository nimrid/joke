package ng.com.gocheck.joke.networkCall

class JokeCategoryRepo(private val api : ApiRequest) : SafeApiRequest() {

    suspend fun programJokes() = apiRequest { api.programmingJoke() }

    suspend fun miscellaneousJokes() = apiRequest { api.miscellaneous() }

    suspend fun darkJokes() = apiRequest { api.darkJoke() }

    suspend fun xmasJokes() = apiRequest { api.xmasJoke() }

    suspend fun punJokes() = apiRequest { api.punJoke() }

    suspend fun spookyJokes() = apiRequest { api.spookyJoke() }
}