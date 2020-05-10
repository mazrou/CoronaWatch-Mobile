package solutus.coronawatch.data.reposetory


import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.db.entity.RegisterPostRequest
import solutus.coronawatch.data.db.entity.Token
import solutus.coronawatch.data.internal.GetDataFromApiException
import solutus.coronawatch.data.network.SafeApiRequest
import solutus.coronawatch.data.network.UserApi

class UserRepository(
    private val userApi: UserApi
) : SafeApiRequest() {

    suspend fun getAuthAppUser(token : String) : AppUser?  {
        return  userApi.getAuthUser(token).body()
    }


    suspend fun getUser(id:Int) : AppUser{
        return userApi.getUser(id).body()!!

    }

    suspend fun registerUser(email : String,first_name:String,last_name:String,password:String,second_password:String) : AppUser?{
        val postRequest = RegisterPostRequest(email,first_name,last_name,password,second_password)
        return userApi.addUser(postRequest).body()
    }

    suspend fun loginUser(emailPassword : HashMap<String,String>): Token? {
        return try {
            apiRequest { userApi.loginUser(emailPassword) }
        } catch (e : GetDataFromApiException){
            e.printStackTrace()
            e.message
            null
        }
    }
}