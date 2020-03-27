package solutus.coronawatch.model

import android.media.Image
import android.provider.MediaStore

data class Video( var title : String,
                  val publisher : User,
                  var screen  : Image,
                  val video : MediaStore.Video)

