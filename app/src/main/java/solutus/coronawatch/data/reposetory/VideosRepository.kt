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
        listv.add(Video(id = "video_1", publisher = User("2","AYoub"), title = "أول فيديو",url = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4", comments = null ,thumbnil = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"))
        listv.add(Video(id = "video_1", publisher = User("2","AYoub"), title = "أول فيديو",url = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4", comments = null ,thumbnil = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"))
        listv.add(Video(id = "video_1", publisher = User("2","AYoub"), title = "أول فيديو",url = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4", comments = null ,thumbnil = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"))
        listv.add(Video(id = "video_1", publisher = User("2","AYoub"), title = "أول فيديو",url = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4", comments = null , thumbnil = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"))
        return listv
    }
}