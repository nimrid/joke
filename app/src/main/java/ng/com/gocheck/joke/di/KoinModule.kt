package ng.com.gocheck.joke.di

import ng.com.gocheck.joke.model.db.JokeDb
import ng.com.gocheck.joke.networkCall.ApiRequest
import ng.com.gocheck.joke.networkCall.NetworkConnection
import ng.com.gocheck.joke.networkCall.Repository
import ng.com.gocheck.joke.viewmodel.JokeViewModel
import ng.com.gocheck.joke.viewmodel.JokeViewModelFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
//Checking out Koin dependency injection
val NetworkModule = module {
    factory { androidContext().applicationContext }

    single { NetworkConnection(get()) }

//    single { JokeViewModelFactory(get()) }
}

val ApiModule = module {
    single { ApiRequest() }
}

val JokeModule = module {
    single { JokeDb(get()) }
}

val RepositoryModule = module {
    single { Repository(get(), get()) }
}

val viewMudule = module {
    viewModel { JokeViewModel(get()) }
}