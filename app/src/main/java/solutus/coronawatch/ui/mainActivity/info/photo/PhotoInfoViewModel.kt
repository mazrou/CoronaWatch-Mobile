package solutus.coronawatch.ui.mainActivity.info.photo

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import solutus.coronawatch.data.entity.Case

class PhotoInfoViewModel : ViewModel() {
    var photoPath: String? = null

    fun uploadCase(context: Context?, description: String) {
        val case = Case(description, photoPath!!, "PHOTO")
        Toast.makeText(context, "$photoPath  $description", Toast.LENGTH_SHORT).show()
        //TODO : Completer l'integration avec l'api ici
    }
}
