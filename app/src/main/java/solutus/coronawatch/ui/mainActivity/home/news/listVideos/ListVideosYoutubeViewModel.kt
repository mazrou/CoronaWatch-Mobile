package solutus.coronawatch.ui.mainActivity.home.news.listVideos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import solutus.coronawatch.data.entity.VideoYoutube
import solutus.coronawatch.data.reposetory.implementation.VideosYoutubeRepository
import solutus.coronawatch.utilities.Coroutines

class ListVideosYoutubeViewModel (private val videosYoutubeRepository: VideosYoutubeRepository) : ViewModel() {
    private lateinit var job: Job
    private val _videosYoutube = MutableLiveData<List<VideoYoutube>>()

    val videosYoutube: LiveData<List<VideoYoutube>>
        get() = _videosYoutube

    //to change later
    fun getVideosYoutube() {
        job = Coroutines.ioThMain(
            { videosYoutubeRepository.getVideosYoutube() },
            { _videosYoutube.value = it }
        )
    }

    override fun onCleared() {
        if (::job.isInitialized) {
            job.cancel()
        }
    }
}
