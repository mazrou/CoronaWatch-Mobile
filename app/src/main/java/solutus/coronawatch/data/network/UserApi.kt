package solutus.coronawatch.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.db.entity.RegisterPostRequest
import solutus.coronawatch.data.db.entity.Token
import java.util.concurrent.TimeUnit


private const val BASE_URL = "http://solutusprojet.herokuapp.com/api-account/"

interface UserApi {


    @GET("current-user")
    suspend fun getAuthUser(
        @Header("Authorization") token: String
    ) : Response<AppUser>

    @GET("user/{id}")
    suspend fun getUser(
        @Path("id") id : Int
    ): Response<AppUser>


    @POST("register/")
    suspend fun addUser(
        @Body registerPostRequest : RegisterPostRequest
    ) : Response<AppUser>

    @POST("login/visitor/")
    suspend fun loginUser(
        @Body emailPassword : HashMap<String , String>
    ) : Response<Token>


    companion object {
        operator fun invoke() : UserApi {
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
                .create(UserApi::class.java)

        }
    }
}