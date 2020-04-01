package solutus.coronawatch.data.db.entity

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable


 data class Video(
     override  val id : String,
     override  val publisher : User,
     var title : String,
     var url : String,
     // var screen  : Image,
     //val video : MediaStore.Video
     override var comments: List<Comment>?
 ) :
     Publication(
         id,
         publisher,
         comments
     )



