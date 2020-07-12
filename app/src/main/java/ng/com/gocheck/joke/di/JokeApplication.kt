package ng.com.gocheck.joke.di

import android.app.Application
import ng.com.gocheck.joke.model.db.JokeDb
import ng.com.gocheck.joke.networkCall.ApiRequest
import ng.com.gocheck.joke.networkCall.NetworkConnection
import ng.com.gocheck.joke.networkCall.Repository
import ng.com.gocheck.joke.viewmodel.JokeViewModelFactory
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

@Suppress("unused")
class JokeApplication : Application(), DIAware {

    override val di by DI.lazy {
        import(androidXModule(this@JokeApplication))
        /* bindings */
        bind() from singleton { NetworkConnection(instance()) }
        bind() from singleton { ApiRequest() }
        bind() from singleton { JokeDb(instance()) }
        bind() from singleton { Repository(instance(), instance()) }
        bind() from provider { JokeViewModelFactory(instance())}
    }

}