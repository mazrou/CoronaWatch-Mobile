package solutus.coronawatch.data.db.entity

import com.google.gson.annotations.SerializedName

data class GetPostsResponse (
    @SerializedName("count")
    val count : Int,
    @SerializedName("next")
    val next : String,
    @SerializedName("previous")
    val previous : String,
    @SerializedName("results")
    val posts : ArrayList<Post>

)