package com.example.coronawatch_mobile.repository

import org.junit.Assert
import org.junit.Test
import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.reposetory.VideosRepository


class ViddeosRepositoryTest {
    @Test
    fun getVideosTest(){
        Assert.assertEquals(4, VideosRepository(CoronaWatchApi.invoke()).getVideos().size)
    }
}