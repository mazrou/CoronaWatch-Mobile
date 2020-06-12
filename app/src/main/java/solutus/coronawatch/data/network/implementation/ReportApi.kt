package solutus.coronawatch.data.network.implementation

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import solutus.coronawatch.data.entity.Location
import solutus.coronawatch.data.network.abstraction.Api
import solutus.coronawatch.data.network.abstraction.SERVER_URL
import solutus.coronawatch.data.network.entity.Token


private const val BASE_URL: String = SERVER_URL +"api-report/"
interface ReportApi  : Api{

    @Multipart
    @POST("case/report/")
    suspend fun reportCase(
        @Header("Authorization") token: Token,
        @Part("description") description: String,
        @Part file: MultipartBody.Part,
        @Part("location") location : Location
    ) : Response<Unit>

    companion object {
        operator fun invoke() : ReportApi {
            return Api().baseUrl(BASE_URL)
                .build()
                .create(ReportApi::class.java)

        }
    }
}