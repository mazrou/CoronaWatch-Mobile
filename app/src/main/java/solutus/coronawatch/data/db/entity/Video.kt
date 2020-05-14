package solutus.coronawatch.data.db.entity

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable


 data class Video(

     override  var id : String,
     override  var publisher : User,
     override var comments: List<Comment>?,
     override var title : String,
     override var url : String?,
     var thumbnil : String

 ) :
     Publication(
         id,
         publisher,
         title,
         url,
         comments
     )



