package solutus.coronawatch

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
//import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*
import solutus.coronawatch.ui.home.videos.VideosViewModelFactory

class CoronaWatch() : Application() , KodeinAware {
    override val kodein = Kodein.lazy {

       // import(androidXModule(this))
        bind() from provider { VideosViewModelFactory(instance()) }
    }
}