package solutus.coronawatch.data.network.implementation

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import solutus.coronawatch.data.network.abstraction.Api
import solutus.coronawatch.data.network.abstraction.SERVER_URL

private const val BASE_URL: String = SERVER_URL +"social/"
interface SocialAPi : Api {


    @GET("login/facebook/")
    suspend fun loginFacebook() : Response<Unit>

    companion object{
        operator fun invoke() : SocialAPi {
            return Api().baseUrl(BASE_URL)
                .build()
                .create(SocialAPi::class.java)
        }
    }
}