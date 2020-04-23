package solutus.coronawatch.data.db.entity

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable


 data class Video(

     override  val id : String,
     override  val publisher : User,
     override var comments: List<Comment>?,

     var title : String,
     var url : String,
     var thumbnail : String

 ) :
     Publication(
         id,
         publisher,
         comments
     )



