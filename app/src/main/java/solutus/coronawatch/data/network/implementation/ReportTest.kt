 package solutus.coronawatch.data.network.implementation

 import okhttp3.MultipartBody
 import okhttp3.OkHttpClient
 import okhttp3.RequestBody
 import okhttp3.logging.HttpLoggingInterceptor
 import retrofit2.Response
 import retrofit2.Retrofit
 import retrofit2.converter.gson.GsonConverterFactory
 import retrofit2.http.Multipart
 import retrofit2.http.POST
 import retrofit2.http.Part
 import java.util.concurrent.TimeUnit

 interface ReportTest {



     @Multipart
     @POST("post")
     suspend fun testRequest(
        @Part file : MultipartBody.Part ,
        @Part("Description") description : RequestBody
     ):Response<Unit>


    companion object{

            operator fun invoke() : ReportTest {
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
                    .addConverterFactory(GsonConverterFactory.create()).
                        baseUrl("https://ptsv2.com/t/7luos-1593774482/")
                    .build()
                    .create(ReportTest::class.java)


            }
        }

}