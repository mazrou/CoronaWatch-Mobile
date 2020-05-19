package solutus.coronawatch.utilities

import android.content.Context
import solutus.coronawatch.data.network.implementation.ConnectivityInterceptorImpl
import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.reposetory.implementation.ContentRepository
import solutus.coronawatch.ui.mainActivity.home.videos.VideoViewModelFactory

object InjectorUtils {

    fun provideVideosViewModelFactory( context: Context) : VideoViewModelFactory {
        val contentRepository =
            ContentRepository(
                ContentApi(ConnectivityInterceptorImpl(context))
            )
        return VideoViewModelFactory(
            contentRepository
        )
    }
}