package ng.com.gocheck.joke.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ng.com.gocheck.joke.networkCall.Repository

@Suppress("UNCHECKED_CAST")
class JokeViewModelFactory (private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokeViewModel(repository) as T
    }

}