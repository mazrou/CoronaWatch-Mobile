package solutus.coronawatch.data.db.entity


import com.google.gson.annotations.SerializedName

data class AppUser (
    @SerializedName("pk")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("birth_date")
    val birthDate: String,
    val email: String,
    @SerializedName("last_login")
    val lastLogin: Any,

    @SerializedName("date_joined")
    val dateJoined: String,


    @SerializedName("is_active")
    val isActive: Boolean, // true

    val role: Int ,
    val password :String
)