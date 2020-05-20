package solutus.coronawatch.data.network.implementation

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import solutus.coronawatch.data.entity.DeletePostRequest
import solutus.coronawatch.data.entity.GetPostsResponse
import solutus.coronawatch.data.entity.Post
import solutus.coronawatch.data.network.abstraction.Api
import solutus.coronawatch.data.network.abstraction.SERVER_URL
import java.util.concurrent.TimeUnit


private const val BASE_URL : String =  SERVER_URL +"api-content/"

interface ContentApi : Api {

    @GET("posts/")
    suspend fun getPosts(
    ) : Response<GetPostsResponse>

    @GET("posts/user/")
    suspend fun getUserPosts(
        @Header("Authorization") token :String
    ): Response<GetPostsResponse>

    @Multipart
    @POST("post/create/")
    suspend fun storePost(
        @Header("Authorization") token: String,
        @Part("title") title : String,
        @Part("content") content : String,
        @Part file : MultipartBody.Part

    )

    @PUT("post/delete/{id}")
    suspend fun deletePost(
        @Header("Authorization") token :String,
        @Path ("id") id : Int,
        @Body deleted : DeletePostRequest
    ) : Response<Post>

    companion object {
        operator fun invoke() : ContentApi {

            return Api().baseUrl(BASE_URL)
                    .build()
                    .create(ContentApi::class.java)

        }
    }

}