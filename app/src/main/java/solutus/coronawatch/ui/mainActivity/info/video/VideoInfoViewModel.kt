package solutus.coronawatch.ui.mainActivity.info.video

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import solutus.coronawatch.data.entity.Case
import solutus.coronawatch.ui.mainActivity.info.InfoFragmentViewModel

class VideoInfoViewModel : ViewModel() {
    var videoPath: String? = null

    fun uploadCase(context: Context?, description: String) {
        val case = Case(description, InfoFragmentViewModel.location, videoPath!!, "VIDEO")
        Toast.makeText(
            context,
            "$videoPath  $description ${InfoFragmentViewModel.location.x} ${InfoFragmentViewModel.location.y}",
            Toast.LENGTH_SHORT
        ).show()
        //TODO : Completer l'integration avec l'api ici
    }
}
