package solutus.coronawatch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import solutus.coronawatch.data.db.entity.Video
import solutus.coronawatch.data.reposetory.VideosRepository
import solutus.coronawatch.ui.MainActivity.Companion.user
import solutus.coronawatch.utilities.Coroutines


open class VideosViewModel(
    private val videosRepository: VideosRepository
) : ViewModel() {

    private lateinit var job : Job
    private val _videos = MutableLiveData<List<Video>>()
    private val _userVideos = MutableLiveData<List<Video>>()

    val videos : LiveData<List<Video>>
        get() = _videos


    fun getVideos()  {
        job = Coroutines.ioThMain(
            {videosRepository.getVideos()},
            {_videos.value = it}
        )
    }

    val userVideos : LiveData<List<Video>>
            get() = _userVideos

    fun getUserVideos(){
        job = Coroutines.ioThMain(
            { videosRepository.getVideos().filter { video ->video.publisher.id == user.id }},
            {_userVideos.value = it}
        )
    }
    override fun onCleared() {
        if(::job.isInitialized){
            job.cancel()
        }
    }
}
