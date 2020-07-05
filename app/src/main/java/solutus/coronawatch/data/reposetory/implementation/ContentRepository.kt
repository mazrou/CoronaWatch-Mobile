package solutus.coronawatch.data.reposetory.implementation

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import solutus.coronawatch.data.entity.*
import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.network.implementation.UserApi
import solutus.coronawatch.data.reposetory.abstraction.SafeApiRequest
import solutus.coronawatch.utilities.RealPathUtil
import java.io.File


class ContentRepository (
    private val contentApi: ContentApi
): SafeApiRequest() {


    suspend fun postVideo(token : String , title: String ,content: String,video : MultipartBody.Part  ) {
        contentApi.storePost(token, title, content, video)
    }
    private fun getComment(): List<Comment> {
        val user = AppUser(1, "Aymen", "Ourdjini", "", "", "", "", true, 0, "")
        return arrayListOf(
            Comment("1", user, "comment 1"),
            Comment("2", user, "comment 2"),
            Comment("3", user, "comment 3"),
            Comment("4", user, "comment 4")
        )
    }
    suspend fun postVideo(token : String , title: String ,content: String,video : Uri ,context : Context  ) {

        val realPath :String? = RealPathUtil.getRealPath(context,video)
        val originalFile : File = File(realPath!!)
        val str = context.contentResolver?.getType(video) as String
        val file : RequestBody = RequestBody.create(str.toMediaTypeOrNull(),originalFile)

        val videoP : MultipartBody.Part = MultipartBody.Part.createFormData("file",originalFile.name,file )

        contentApi.storePost(token,title,content,videoP)

    }

    suspend fun getPosts() : ArrayList<Post>? {
        return contentApi.getPosts().body()!!.posts.filter { (it.status == "accepted") && !it.deleted } as ArrayList<Post>?
    }

    suspend fun getUserPosts(token: String) : ArrayList<Post>?{
        return contentApi.getUserPosts("token $token").body()!!.posts
    }

    suspend fun deletePost(token: String,id:Int){
        val deleted = DeletePostRequest("true")
        contentApi.deletePost("token $token",id,deleted)
    }

    suspend fun createVideos(posts : ArrayList<Post>) : ArrayList<Video>{


        val userRepository =
            UserRepository(
                UserApi.invoke()
            )
        lateinit var user : AppUser
        val listv = ArrayList<Video>()

        for (post in posts) {
            user = userRepository.getUser(post.userId)
            listv.add(
                Video(
                    id = post.pk.toString(),
                    publisher = user,
                    title = post.title,
                    url = post.file,
                    content = post.content,
                    //comments = getComments(),
                    thumbnail = "https://www.gynecologie-pratique.com/sites/www.gynecologie-pratique.com/files/images/article_journal/covid-19.png",
                    listComments = getComment()
                )
            )

        }

        return listv
    }

    suspend fun  createUserVideos(posts : ArrayList<Post> , user: AppUser) : ArrayList<Video>{
        val listv = ArrayList<Video>()
        for (post in posts ){
            listv.add(
                Video(
                    id = post.pk.toString(),
                    publisher = user,
                    title = post.title,
                    url = post.file,
                    content = post.content,
                    thumbnail = "https://www.gynecologie-pratique.com/sites/www.gynecologie-pratique.com/files/images/article_journal/covid-19.png",
                    listComments = getComment()
                )
            )
        }
        return listv
    }


}