package ng.com.gocheck.joke.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import ng.com.gocheck.joke.Coroutine
import ng.com.gocheck.joke.model.Jokes
import ng.com.gocheck.joke.networkCall.JokeCategoryRepo

class JokeCategoryVM(private val repo : JokeCategoryRepo) : ViewModel() {

    private lateinit var job : Job

    private val _joke = MutableLiveData<Jokes>()
    val joke : LiveData<Jokes>
        get() = _joke
    
    fun getMisscellJoke() {
        job = Coroutine.main {
            val joke = repo.miscellaneousJokes()
            _joke.value = joke
        }
    }

    fun getDarkJoke(){
        job = Coroutine.main {
            val joke = repo.darkJokes()
            _joke.value = joke
        }
    }

    fun getXmas() {
        job = Coroutine.main {
            val joke = repo.xmasJokes()
            _joke.value = joke
        }
    }

    fun getProgramJoke() {
        job = Coroutine.main {
            val joke = repo.programJokes()
            _joke.value = joke
        }
    }

    fun getPunJoke() {
        job = Coroutine.main {
            val joke = repo.punJokes()
            _joke.value = joke
        }
    }

    fun getSpookyJoke() {
        job = Coroutine.main {
            val joke = repo.spookyJokes()
            _joke.value = joke
        }
    }

    override fun onCleared(){
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

}