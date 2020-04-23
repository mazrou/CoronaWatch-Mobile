package solutus.coronawatch.data.reposetory


import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.db.entity.Token
import solutus.coronawatch.data.internal.GetDataFromApiException
import solutus.coronawatch.data.network.SafeApiRequest
import solutus.coronawatch.data.network.UserApi

class UserRepository(
    private val userApi: UserApi
) : SafeApiRequest() {

    suspend fun getAppUser(id : Int) : AppUser?  = apiRequest{ userApi.getUser(id) }

    suspend fun addUser(user : AppUser): AppUser? {
        return try {
           return apiRequest{userApi.addUser(user)}
        }catch (e : GetDataFromApiException){
            e.message
            e.printStackTrace()
            null
        }

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