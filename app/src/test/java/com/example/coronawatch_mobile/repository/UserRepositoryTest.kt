package com.example.coronawatch_mobile.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.network.UserApi
import solutus.coronawatch.data.reposetory.UserRepository
import solutus.coronawatch.data.reposetory.VideosRepository

class UserRepositoryTest{
    private val userRepository = UserRepository(UserApi.invoke())

    @Test
     fun getAppUserTest() = runBlocking{
       val id = 1

       val appUser =  userRepository.getAppUser(id)
       Assert.assertEquals(1 ,appUser!!.id)
    }

    @Test
    fun addUserTest() = runBlocking{

        val user  = AppUser(
            1324,
            "Mohamed",
            "Mazrou",
            "26-04-2000",
            "g@gmail.com",
            "23-4-2020",
            "23-4-2020",
            true,
            2,
            "nitrou"
        )
        val appUser =  userRepository.addUser(user)

        Assert.assertEquals(appUser , null)
    }

    @Test
    fun loginUserTest() = runBlocking {
        val emailPassword = HashMap<String , String>()
        emailPassword["email"] = "ga_mazrou@esi.dz"
        emailPassword["password"] = "nitrou18b_boy"
        Assert.assertEquals("f50f1067a2f33109abbf197f165971678c460d02", userRepository.loginUser(emailPassword)!!.token)
    }
}