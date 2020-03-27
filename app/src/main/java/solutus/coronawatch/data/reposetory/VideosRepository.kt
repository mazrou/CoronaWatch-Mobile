package solutus.coronawatch.data.reposetory

import android.media.Image
import solutus.coronawatch.data.entity.User
import solutus.coronawatch.data.entity.Video
import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.network.SafeApiReaquest

class VideosRepository(
    private val coronaWatchApi: CoronaWatchApi
) : SafeApiReaquest() {


    //suspend fun getVideos() =  apiRequest{ coronaWatchApi.getVideos() }
      fun getVideos(): List<Video> {
        var listv = ArrayList<Video>()
        listv.add(Video("hadi temchi haka ", User("2","AYoub")))
        listv.add(Video("hadi temchi haka ", User("2","AYoub")))
        listv.add(Video("hadi temchi haka ", User("2","AYoub")))
        listv.add(Video("hadi temchi haka ", User("2","AYoub")))
        return listv
    }
}