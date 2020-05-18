package solutus.coronawatch.data.reposetory.implementation

import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.db.entity.Post
import solutus.coronawatch.data.db.entity.Video
import solutus.coronawatch.data.network.entity.UploadedVideo
import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.reposetory.abstraction.Repository
import solutus.coronawatch.data.reposetory.abstraction.ContentRepository

class ContentRepositoryImp (
    private val contentApi: ContentApi
) : Repository(contentApi) , ContentRepository {

     /**
      * Don't forget to use for the request the function
      * apiRequest { api.request() }
      * */
    override suspend fun getPosts(): ArrayList<Post>? {
        TODO("Not yet implemented")
    }

    override suspend fun postVideo(video: UploadedVideo) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserPosts(token: String): ArrayList<Post>? {
        TODO("Not yet implemented")
    }

    override suspend fun deletePost(token: String, id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun createVideos(posts: ArrayList<Post>): ArrayList<Video> {
        TODO("Not yet implemented")
    }

    override fun createUserVideos(posts: ArrayList<Post>, user: AppUser): ArrayList<Video> {
        TODO("Not yet implemented")
    }
}