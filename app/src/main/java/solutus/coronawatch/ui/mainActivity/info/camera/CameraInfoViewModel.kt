package solutus.coronawatch.ui.mainActivity.info.camera

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import solutus.coronawatch.data.entity.Case

class CameraInfoViewModel : ViewModel() {
    var mediaPath: String? = null
    var type: String? = null

    fun uploadCase(
        context: Context?,
        description: String
    ) {
        val case = Case(description, mediaPath!!, type!!)
        Toast.makeText(context, "$mediaPath  $description $type", Toast.LENGTH_SHORT).show()
        //TODO : Completer l'integration avec l'api ici
    }
}
