package solutus.coronawatch.data.reposetory.implementation

import solutus.coronawatch.data.entity.VideoYoutube
import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.reposetory.abstraction.SafeApiRequest

class VideosYoutubeRepository(private val contentApi: ContentApi) : SafeApiRequest() {

    //pour tester
    fun getVideosYoutube():List<VideoYoutube>{
        val list = ArrayList<VideoYoutube>()
        list.add(VideoYoutube("1","Corona1","https://www.youtube.com/watch?v=RBUbja6V9Aw","https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"))
        list.add(VideoYoutube("1","Corona2","https://www.youtube.com/watch?v=RBUbja6V9Aw","https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"))
        return list
    }
}