package solutus.coronawatch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.db.entity.Post
import solutus.coronawatch.data.db.entity.Video
import solutus.coronawatch.data.reposetory.ContentRepository
import solutus.coronawatch.data.reposetory.VideosRepository
import solutus.coronawatch.utilities.Coroutines


open class VideosViewModel(
    private val contentRepository : ContentRepository
) : ViewModel() {

    private lateinit var job : Job
    private val _videos = MutableLiveData<List<Video>>()
    private val _userVideos = MutableLiveData<List<Video>>()

    val videos : LiveData<List<Video>>
        get() = _videos


    fun getVideos(posts: ArrayList<Post>) {

        job = Coroutines.ioThMain(
            {contentRepository.createVideos(posts)},
            {_videos.value = it}
        )
    }

    fun getUserVideos(posts: ArrayList<Post>,user: AppUser) {

        job = Coroutines.ioThMain(
            {contentRepository.createUserVideos(posts,user)},
            {_userVideos.value = it}
        )
    }


    val userVideos : LiveData<List<Video>>
            get() = _userVideos

    /*fun getUserVideos(){
        job = Coroutines.ioThMain(
            { videosRepository.getVideos().filter { video ->video.publisher.id == user.id }},
            {_userVideos.value = it}
        )
    }*/
    override fun onCleared() {
        if(::job.isInitialized){
            job.cancel()
        }
    }
}
