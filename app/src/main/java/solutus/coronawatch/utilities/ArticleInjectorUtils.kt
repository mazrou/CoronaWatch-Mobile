package solutus.coronawatch.utilities

import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.reposetory.NouveltiesRepository
import solutus.coronawatch.factory.NouveltiesViewModelFactory

object ArticleInjectorUtils {

    fun provideArticleViewModelFactory() : NouveltiesViewModelFactory {
        val articleRepository = NouveltiesRepository(CoronaWatchApi())
        return NouveltiesViewModelFactory(
            articleRepository
        )
    }
}