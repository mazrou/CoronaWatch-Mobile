package solutus.coronawatch.data.reposetory

import solutus.coronawatch.data.db.entity.Post
import solutus.coronawatch.data.internal.GetDataFromApiException
import solutus.coronawatch.data.network.ContentApi
import solutus.coronawatch.data.network.SafeApiRequest

class ContentRepository (
    private val contentApi: ContentApi
): SafeApiRequest() {

    suspend fun storePost(post: Post) : Post?{
        return try{
            apiRequest { contentApi.storePost(post)}
        }catch (e :GetDataFromApiException){
            e.message
            e.printStackTrace()
            null

        }

    }

}