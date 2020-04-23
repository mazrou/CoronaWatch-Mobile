package solutus.coronawatch.data.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import solutus.coronawatch.data.db.entity.Post

private const val BASE_URL = "http://solutusprojet.herokuapp.com/api-content/"

interface ContentApi {


    @POST("post/create/")
    suspend fun storePost(
        @Body post : Post
    ) : Response<Post>



    companion object {
        operator fun invoke() : ContentApi {

            return  Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ContentApi::class.java)

        }
    }

}