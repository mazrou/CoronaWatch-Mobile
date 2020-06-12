package solutus.coronawatch.ui.mainActivity.info.photo

import android.content.Context
import android.net.Uri
import android.os.FileUtils
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import solutus.coronawatch.data.entity.Case
import solutus.coronawatch.data.internal.GetDataFromApiException
import solutus.coronawatch.data.network.entity.Token
import solutus.coronawatch.data.network.implementation.ReportApi
import solutus.coronawatch.data.reposetory.implementation.ReportRepository
import solutus.coronawatch.utilities.RealPathUtil
import java.io.File

class PhotoInfoViewModel(
    var repository : ReportRepository
) : ViewModel() {
    var photoPath: String? = null
    var photoUri : Uri? = null


    fun uploadCase(context: Context, description: String) {

        val case = Case(description, "photoPath!!", "PHOTO")
        try {
            //Toast.makeText(context, "$photoPath  $description", Toast.LENGTH_SHORT).show()

            val realPath :String? = RealPathUtil.getRealPath(context,photoUri!!)
            val originalFile : File = File(realPath!!)
            val str = context.contentResolver?.getType(photoUri!!) as String
            val file : RequestBody = RequestBody.create(str.toMediaTypeOrNull(),originalFile)

            val image : MultipartBody.Part = MultipartBody.Part.createFormData("file",originalFile.name,file )
            var success = false
           val job =  CoroutineScope(IO).launch {
                try {
                    println("Debug : Sending the file on the network")
                    repository.reportImage(description , image)
                    success = true
                }catch (e : Exception){
                    println("Debug : ${e.message}")
                    e.printStackTrace()
                   CoroutineScope(Main).launch {
                       Toast.makeText(context, " لم يتم الابلاغ بنجاح يرجى المحاولة مجددا  ${e.message}", Toast.LENGTH_LONG).show()
                   }
                }
            }

            if(job.isCompleted){
                if(success)
                    Toast.makeText(context, " تم الابلاغ بنجاحا", Toast.LENGTH_LONG).show()
            }
        }catch (e : Exception){
            println("Debug : ${e.cause}")
            e.printStackTrace()
        }
    }
}
