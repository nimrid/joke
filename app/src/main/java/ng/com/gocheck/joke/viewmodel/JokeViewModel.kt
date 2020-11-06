package ng.com.gocheck.joke.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import ng.com.gocheck.joke.Coroutine
import ng.com.gocheck.joke.model.Jokes
import ng.com.gocheck.joke.networkCall.Repository

class JokeViewModel (private val repository: Repository) : ViewModel() {

    private lateinit var job : Job

    private val _joke = MutableLiveData<Jokes>()
    val joke : LiveData<Jokes>
        get() = _joke

//    from db
    fun retrieveDb() = repository.retrieveJoke()

    fun getJoke() {
        job = Coroutine.main {
            val joke = repository.getJoke()
            persistDataSource(joke)
            _joke.value = joke
        }
    }

    private fun persistDataSource(jokes: Jokes){
        job = Coroutine.main {
            repository.saveJoke(jokes)
        }
    }

    override fun onCleared(){
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

}

