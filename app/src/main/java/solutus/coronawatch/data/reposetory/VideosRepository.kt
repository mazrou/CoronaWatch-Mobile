package solutus.coronawatch.data.reposetory

import android.net.Uri
import solutus.coronawatch.data.db.entity.User
import solutus.coronawatch.data.db.entity.Video
import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.network.SafeApiReaquest
import java.security.AccessController.getContext


class VideosRepository(
    private val coronaWatchApi: CoronaWatchApi
) : SafeApiReaquest() {


    //suspend fun getVideos() =  apiRequest{ coronaWatchApi.getVideos() }
      fun getVideos(): List<Video> {
      //  getApplicationContext().getPackageName();
        var listv = ArrayList<Video>()
        listv.add(Video(id = "video_1", publisher = User("2","AYoub"), title = "أول فيديو",url = "yKeULpr9fcg", comments = null))
        listv.add(Video(id = "video_1", publisher = User("2","AYoub"), title = "أول فيديو",url = "yKeULpr9fcg", comments = null))
        listv.add(Video(id = "video_1", publisher = User("2","AYoub"), title = "أول فيديو",url = "yKeULpr9fcg", comments = null))
        listv.add(Video(id = "video_1", publisher = User("2","AYoub"), title = "أول فيديو",url = "yKeULpr9fcg", comments = null))
        return listv
    }
}