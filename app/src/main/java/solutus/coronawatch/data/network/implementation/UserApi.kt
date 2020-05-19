package solutus.coronawatch.data.network.implementation

import retrofit2.Response
import retrofit2.http.*
import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.db.entity.RegisterPostRequest
import solutus.coronawatch.data.network.entity.Token
import solutus.coronawatch.data.network.abstraction.Api
import solutus.coronawatch.data.network.abstraction.ConnectivityInterceptor
import solutus.coronawatch.data.network.abstraction.SERVER_URL


private const val BASE_URL: String = SERVER_URL +"api-account/"

interface UserApi : Api {


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
        operator fun invoke(
            connectivityInterceptor : ConnectivityInterceptor
        ) : UserApi {

            return Api(connectivityInterceptor).baseUrl(BASE_URL)
                .build()
                .create(UserApi::class.java)

        }
    }
}