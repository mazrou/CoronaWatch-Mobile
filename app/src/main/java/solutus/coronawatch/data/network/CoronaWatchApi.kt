package solutus.coronawatch.data.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import solutus.coronawatch.data.entity.Video

private const val BASE_URL = "https://some-thinghere"

interface CoronaWatchApi {


    // et faire la meme chose avec tous les objet a retenir
    @GET("video")
    suspend fun getVideos(
        //@Query("nom du paramatre") npq : Any
    ) : Response<List<Video>>

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