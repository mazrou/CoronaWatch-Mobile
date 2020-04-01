package solutus.coronawatch.ui.user.fragment.content.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import solutus.coronawatch.data.db.entity.Video
import solutus.coronawatch.data.reposetory.VideosRepository
import solutus.coronawatch.utilities.Coroutines

class ViewContentViewModel(
    private val videosRepository: VideosRepository
) : ViewModel() {

    private lateinit var job : Job
    private val _videos = MutableLiveData<List<Video>>()

    val videos : LiveData<List<Video>>
        get() = _videos


    fun getVideos()  {
        job = Coroutines.ioThMain(
            {videosRepository.getVideos()},
            {_videos.value = it}
        )
    }

    override fun onCleared() {
        if(::job.isInitialized){
            job.cancel()
        }
    }
}