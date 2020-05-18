package solutus.coronawatch.data.db.entity

import com.google.gson.annotations.SerializedName

data class DeletePostRequest(
    @SerializedName("deleted")
    val deleted : String
)