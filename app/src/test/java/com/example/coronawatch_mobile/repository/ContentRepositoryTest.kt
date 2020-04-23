package com.example.coronawatch_mobile.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

import solutus.coronawatch.data.db.entity.Post
import solutus.coronawatch.data.network.ContentApi
import solutus.coronawatch.data.reposetory.ContentRepository
import java.util.*
import kotlin.collections.HashMap

class ContentRepositoryTest {

    private val contentRepository = ContentRepository(ContentApi.invoke())


    @Test
    fun storePost() = runBlocking {
        val post =Post(
        "Test from unit ",
         "This is the first post form the mobile app",
        19)

        Assert.assertEquals(null , contentRepository.storePost(post)!!.title)
    }
}