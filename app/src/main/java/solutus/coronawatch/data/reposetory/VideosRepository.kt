package solutus.coronawatch.data.reposetory

import android.net.Uri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import solutus.coronawatch.data.db.entity.*
import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.network.SafeApiReaquest
import solutus.coronawatch.data.network.UserApi
import java.security.AccessController.getContext


class VideosRepository(
    private val coronaWatchApi: CoronaWatchApi
) : SafeApiReaquest() {
    private val userRepository = UserRepository(UserApi.invoke())
    private lateinit var user : AppUser
/*private fun getComments(post : Post,user: AppUser):List<Comment>{
    val comments = ArrayList<Comment> ()
    comments.add(0,Comment("saber",,))
    return comments
}*/

    //suspend fun getVideos() =  apiRequest{ coronaWatchApi.getVideos() }
    fun getVideos(posts : ArrayList<Post>): List<Video> {
        println("HELLO 333")
        //  getApplicationContext().getPackageName();
       val listv = ArrayList<Video>()
         for (post in posts) {
            CoroutineScope(IO).launch{
               user = userRepository.getUser(post.pk)

            listv.add(
                Video(
                    id = post.pk.toString(),
                    publisher =user,
                    title = post.title,
                    url = post.file,
                    //comments = getComments(),
                    thumbnail = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"
                )
            )

            }
        }
        return listv
    }
}