package ng.com.gocheck.joke.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
//Checking out koin dependency injection
class KoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@KoinApplication)
            listOf( NetworkModule, JokeModule, ApiModule, viewMudule, RepositoryModule)
        }
    }

}