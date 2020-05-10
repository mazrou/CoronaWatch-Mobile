package solutus.coronawatch.data.reposetory

import android.content.Context
import android.net.Uri
import android.os.FileUtils
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.db.entity.Post
import solutus.coronawatch.data.db.entity.Video

import solutus.coronawatch.data.network.ContentApi
import solutus.coronawatch.data.network.SafeApiRequest
import solutus.coronawatch.data.network.UserApi
import solutus.coronawatch.utilities.RealPathUtil
import java.io.File


class ContentRepository (
    private val contentApi: ContentApi
): SafeApiRequest() {

    suspend fun postVideo(user:AppUser ,token : String , title: String ,content: String,video : Uri ,context : Context  ) {
        val realPath :String? = RealPathUtil.getRealPath(context,video)
        val originalFile : File = File(realPath)
        val str = context?.contentResolver?.getType(video) as String
        val file : RequestBody = RequestBody.create(str.toMediaTypeOrNull(),originalFile)
        val videoP : MultipartBody.Part = MultipartBody.Part.createFormData("video",originalFile.name,file )

        println("//////////////////////////////////////")
        contentApi.storePost(token,title,content)
    }


    suspend fun getPosts() : ArrayList<Post>? {
       return contentApi.getPosts().body()
    }

    suspend fun createVideos(posts : ArrayList<Post>) : ArrayList<Video>{


        val userRepository = UserRepository(UserApi.invoke())
        lateinit var user : AppUser
        val listv = ArrayList<Video>()
        for (post in posts){
            println()
        }
        for (post in posts) {
                user = userRepository.getUser(post.userId)
                listv.add(
                    Video(
                        id = post.pk.toString(),
                        publisher = user,
                        title = post.title,
                        url = post.file,
                        //comments = getComments(),
                        thumbnail = "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"
                    )
                )

            }

        return listv
    }



    /*suspend fun storePost(title : String , content : String ) : Post?{
        return try{
            apiRequest { contentApi.storePost(title,content)}
        }catch (e :GetDataFromApiException){
            e.message
            e.printStackTrace()
            null

        }

    }*/

}