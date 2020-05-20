package solutus.coronawatch.data.reposetory.implementation


import solutus.coronawatch.data.entity.AppUser
import solutus.coronawatch.data.entity.RegisterPostRequest
import solutus.coronawatch.data.network.entity.Token
import solutus.coronawatch.data.network.implementation.UserApi
import solutus.coronawatch.data.reposetory.abstraction.Repository
import solutus.coronawatch.data.reposetory.abstraction.UserRepository

class UserRepositoryImp (
    private val userApi : UserApi
) : Repository(userApi) ,  UserRepository {

    /**
     * Don't forget to use for the request the function
     * apiRequest { api.request() }
     *
     * to use the token
     *      super.token
     * but don't put it on the title of the methods
     * */


    override suspend fun getAuthAppUser(): AppUser? {
        TODO("Not yet implemented")

    }

    override suspend fun getUser(id: Int): AppUser {
        TODO("Not yet implemented")
    }

    override suspend fun registerUser(postRequest: RegisterPostRequest): AppUser? {
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(emailPassword: HashMap<String, String>): Token? {
        TODO("Not yet implemented")
    }

    override suspend fun logOut(token: Token) {
        TODO("Not yet implemented")
    }
}