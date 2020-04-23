package solutus.coronawatch.data.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.db.entity.Token



private const val BASE_URL = "http://solutusprojet.herokuapp.com/api-account/"

interface UserApi {


    @GET("user/{pk}")
    suspend fun getUser(
        @Path("pk") pk : Int
    ) : Response<AppUser>

    @POST("add-user/")
    suspend fun addUser(
        @Body user : AppUser
    ) : Response<AppUser>

    @POST("login/visitor/")
    suspend fun loginUser(
        @Body emailPassword : HashMap<String , String>
    ) : Response<Token>


    companion object {
        operator fun invoke() : UserApi {

            return  Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(UserApi::class.java)

        }
    }
}