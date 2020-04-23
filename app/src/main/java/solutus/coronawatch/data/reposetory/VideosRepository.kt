package solutus.coronawatch.data.reposetory

import android.net.Uri
import solutus.coronawatch.data.db.entity.Comment
import solutus.coronawatch.data.db.entity.User
import solutus.coronawatch.data.db.entity.Video
import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.network.SafeApiReaquest
import java.security.AccessController.getContext


class VideosRepository(
    private val coronaWatchApi: CoronaWatchApi
) : SafeApiReaquest() {

private fun getComments():List<Comment>{
    val comment = ArrayList<Comment> ()
    comment.add(
        Comment(
            publisher= User(1,"ourdjini2020","ga_ourdjini@esi.dz","Aymen","ourdjini","04-12-1998","https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"),
            text="comment1")
    )
    comment.add(
        Comment(
            publisher= User(1,"ourdjini2020","ga_ourdjini@esi.dz","Aymen","ourdjini","04-12-1998","https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"),
            text="comment1")
    )
    comment.add(
        Comment(
            publisher= User(1,"ourdjini2020","ga_ourdjini@esi.dz","Aymen","ourdjini","04-12-1998","https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"),
            text="comment1")
    )
    comment.add(
        Comment(
            publisher= User(1,"ourdjini2020","ga_ourdjini@esi.dz","Aymen","ourdjini","04-12-1998","https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"),
            text="comment1")
    )
    return comment
}

    //suspend fun getVideos() =  apiRequest{ coronaWatchApi.getVideos() }
    fun getVideos(): List<Video> {
        //  getApplicationContext().getPackageName();
        val listv = ArrayList<Video>()
        listv.add(
            Video(
                id = "video_1",
                publisher = User(2,"ourdjini2020","ga_ourdjini@esi.dz","Aymen","ourdjini","04-12-1998","https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"),
                title = "فيديو",
                url = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4",
                comments = getComments(),
                thumbnil = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"
            )
        )
        listv.add(
            Video(
                id = "video_1",
                publisher = User(1,"ourdjini2020","ga_ourdjini@esi.dz","Aymen","ourdjini","04-12-1998","https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"),
                title = "أول ",
                url = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4",
                comments = getComments(),
                thumbnil = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"
            )
        )

        return listv

    }
}