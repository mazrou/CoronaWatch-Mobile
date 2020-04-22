package solutus.coronawatch.data.db.entity


import com.google.gson.annotations.SerializedName

data class AppUser(
    @SerializedName("birth_date")
    val birthDate: String, // 2020-04-21
    @SerializedName("date_joined")
    val dateJoined: String, // 2020-04-21
    val email: String, // health-agent@gmail.com
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("is_active")
    val isActive: Boolean, // true
    @SerializedName("last_login")
    val lastLogin: Any, // null
    @SerializedName("last_name")
    val lastName: String,
    val pk: Int, // 4
    val role: Int // 4
)