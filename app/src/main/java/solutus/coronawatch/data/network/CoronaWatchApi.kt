package solutus.coronawatch.data.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.db.entity.Video

private const val BASE_URL = "http://solutusprojet.herokuapp.com/api-account/user/"

interface CoronaWatchApi {


    // et faire la meme chose avec tous les objet a retenir
    @GET("user")
    suspend fun getVideos(
        //@Query("nom du paramatre") npq : Any
    ) : Response<List<Video>>


    @GET("{pk}")
    suspend fun getUser(
        @Path("pk") pk : Int
    ) : Response<AppUser>



    companion object {
        operator fun invoke() : CoronaWatchApi{

            return  Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(CoronaWatchApi::class.java)

        }
    }
}