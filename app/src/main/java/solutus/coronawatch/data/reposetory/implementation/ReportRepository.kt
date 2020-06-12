package solutus.coronawatch.data.reposetory.implementation

import android.media.session.MediaSession
import okhttp3.MultipartBody
import solutus.coronawatch.TokenApp
import solutus.coronawatch.data.entity.Location
import solutus.coronawatch.data.network.entity.Token
import solutus.coronawatch.data.network.implementation.ReportApi
import solutus.coronawatch.data.reposetory.abstraction.SafeApiRequest

class ReportRepository  (
   private val api  : ReportApi
) : SafeApiRequest() {


    suspend fun reportImage(description: String, image: MultipartBody.Part , location : Location) {
        println("Debug : ${TokenApp.token}")
        apiRequest {
            api.reportCase(
                TokenApp.token!! ,
                description,
                image ,
                location
            )
        }
    }
}