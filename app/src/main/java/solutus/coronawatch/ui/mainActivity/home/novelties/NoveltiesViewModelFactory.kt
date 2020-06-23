package solutus.coronawatch.ui.mainActivity.home.novelties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import solutus.coronawatch.data.reposetory.implementation.ArticleRepository

class NoveltiesViewModelFactory(private val articleRepository: ArticleRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoveltiesViewModel(
            articleRepository
        ) as T
    }

}