package solutus.coronawatch.data.db.entity


import com.google.gson.annotations.SerializedName

data class Post(
    val title: String,
    val content: String,
    @SerializedName("user")
    val userId: Int
)