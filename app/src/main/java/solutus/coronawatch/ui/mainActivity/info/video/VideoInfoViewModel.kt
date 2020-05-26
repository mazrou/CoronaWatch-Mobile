package solutus.coronawatch.ui.mainActivity.info.video

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import solutus.coronawatch.data.entity.Case

class VideoInfoViewModel : ViewModel() {
    var videoPath: String? = null

    fun uploadCase(context: Context?, description: String) {
        val case = Case(description, videoPath!!, "VIDEO")
        Toast.makeText(context, "$videoPath  $description", Toast.LENGTH_SHORT).show()
        //TODO : Completer l'integration avec l'api ici
    }
}
