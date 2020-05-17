package solutus.coronawatch.data.network

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.db.entity.DeletePostRequest
import solutus.coronawatch.data.db.entity.GetPostsResponse
import solutus.coronawatch.data.db.entity.Post
import java.util.concurrent.TimeUnit


private const val BASE_URL = "http://solutusprojet.herokuapp.com/api-content/"

interface ContentApi {

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
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY



                val httpClient = OkHttpClient.Builder()
                    .callTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(1000, TimeUnit.SECONDS)
                    .readTimeout(1000, TimeUnit.SECONDS)
                    .writeTimeout(1000, TimeUnit.SECONDS)
                    .addInterceptor(logging)


            return  Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ContentApi::class.java)

        }
    }

}