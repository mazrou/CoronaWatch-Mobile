package solutus.coronawatch.data.reposetory

import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.db.entity.User
import solutus.coronawatch.data.db.entity.Video
import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.network.SafeApiRequest
import java.lang.Exception


class VideosRepository(
    private val coronaWatchApi: CoronaWatchApi
) : SafeApiRequest() {


    //suspend fun getVideos() =  apiRequest{ coronaWatchApi.getVideos() }
      fun getVideos(): List<Video> {
      //  getApplicationContext().getPackageName();
        var listv = ArrayList<Video>()
        listv.add(Video(id = "video_1", publisher = User("2","AYoub"), title = "أول فيديو",url = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4", comments = null ,thumbnail = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"))
        listv.add(Video(id = "video_1", publisher = User("2","AYoub"), title = "أول فيديو",url = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4", comments = null ,thumbnail = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"))
        listv.add(Video(id = "video_1", publisher = User("2","AYoub"), title = "أول فيديو",url = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4", comments = null ,thumbnail = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"))
        listv.add(Video(id = "video_1", publisher = User("2","AYoub"), title = "أول فيديو",url = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4", comments = null , thumbnail = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"))
        return listv
    }

     suspend fun getUsers(): AppUser? {

         var p :AppUser? = null
          try {
              p=  apiRequest { coronaWatchApi.getUser(1) }
          }  catch (e:Exception){
              println("I'm an exception")
              println(e.message)
          }finally {

              if(p == null){
                  println("I'm null")
              }
              else{
                  println("I'm not null , I'm work ")
              }
          }
         return  p


     }

}