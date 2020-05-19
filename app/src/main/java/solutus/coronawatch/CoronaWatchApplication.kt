package solutus.coronawatch

import android.annotation.SuppressLint
import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import solutus.coronawatch.data.network.abstraction.Api
import solutus.coronawatch.data.network.abstraction.ConnectivityInterceptor
import solutus.coronawatch.data.network.implementation.ConnectivityInterceptorImpl
import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.network.implementation.UserApi
import solutus.coronawatch.data.reposetory.implementation.ContentRepository
import solutus.coronawatch.data.reposetory.implementation.UserRepository

@SuppressLint("Registered")
class CoronaWatchApplication : Application() , KodeinAware {
    override val kodein = Kodein.lazy {

        import(androidXModule(this@CoronaWatchApplication))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }

        bind<ContentApi>()  with singleton { ContentApi(instance()) }

        bind<UserApi>()  with singleton { UserApi(instance()) }

        bind() from  singleton { UserRepository(instance()) }

        bind() from singleton { ContentRepository(instance()) }


    }
}