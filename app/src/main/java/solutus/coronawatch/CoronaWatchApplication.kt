package solutus.coronawatch

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import solutus.coronawatch.data.network.NetworkConnexion

class CoronaWatchApplication : Application() , KodeinAware {

    override val kodein = Kodein.lazy{

        import(androidXModule(this@CoronaWatchApplication))

        bind() from singleton { NetworkConnexion(instance()) }

    }
}