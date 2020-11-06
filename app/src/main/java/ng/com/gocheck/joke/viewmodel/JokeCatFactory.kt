package ng.com.gocheck.joke.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ng.com.gocheck.joke.networkCall.JokeCategoryRepo

@Suppress("UNCHECKED_CAST")
class JokeCatFactory(private val repo: JokeCategoryRepo) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = JokeCategoryVM(repo) as T

}