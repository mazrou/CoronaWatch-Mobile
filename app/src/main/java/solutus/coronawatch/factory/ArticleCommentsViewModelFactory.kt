package solutus.coronawatch.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import solutus.coronawatch.viewModel.ArticleCommentsViewModel

open class ArticleCommentsViewModelFactory(

) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleCommentsViewModel() as T
    }

}
