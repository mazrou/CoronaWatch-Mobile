package solutus.coronawatch.data.reposetory.implementation

import android.media.session.MediaSession
import okhttp3.MultipartBody
import solutus.coronawatch.data.network.entity.Token
import solutus.coronawatch.data.network.implementation.ReportApi
import solutus.coronawatch.data.reposetory.abstraction.SafeApiRequest

class ReportRepository  (
   private val api  : ReportApi
) : SafeApiRequest(){



    suspend fun reportImage( description : String  , image : MultipartBody.Part)   {
        apiRequest { api.reportCase(
            Token("token 02d11e14c030dd40c1176365f1210b34066374fe") ,
            description ,
            image
        ) }

    }

}