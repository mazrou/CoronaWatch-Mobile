package solutus.coronawatch

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import solutus.coronawatch.data.network.NetworkConnexion
import solutus.coronawatch.data.network.implementation.ReportApi
import solutus.coronawatch.data.reposetory.implementation.ReportRepository
import solutus.coronawatch.ui.mainActivity.info.camera.CameraInfoViewModel
import solutus.coronawatch.ui.mainActivity.info.photo.PhotoInfoViewModel
import solutus.coronawatch.ui.mainActivity.info.video.VideoInfoViewModel

class CoronaWatchApplication : Application() , KodeinAware {

    override val kodein = Kodein.lazy{

        import(androidXModule(this@CoronaWatchApplication))
        bind() from singleton { NetworkConnexion(instance()) }

        bind() from singleton { ReportRepository(instance()) }
        bind() from singleton { ReportApi() }
        bind<PhotoInfoViewModel>() with provider { PhotoInfoViewModel(instance()) }
        bind<VideoInfoViewModel>() with singleton  { VideoInfoViewModel(instance()) }
        bind<CameraInfoViewModel>() with singleton  { CameraInfoViewModel(instance()) }
    }
}