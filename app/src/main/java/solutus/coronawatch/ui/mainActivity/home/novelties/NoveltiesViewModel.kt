package solutus.coronawatch.ui.mainActivity.home.novelties

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import solutus.coronawatch.data.entity.Article
import solutus.coronawatch.data.reposetory.implementation.ArticleRepository
import solutus.coronawatch.utilities.Coroutines

class NoveltiesViewModel(private val articleRepository: ArticleRepository) : ViewModel() {
    private lateinit var job: Job
    private val _articles = MutableLiveData<List<Article>>()

    val articles: LiveData<List<Article>>
        get() = _articles

    //to change later
    fun getArticles() {
        job = Coroutines.ioThMain(
            { articleRepository.getArticles() },
            { _articles.value = it }
        )
    }

    override fun onCleared() {
        if (::job.isInitialized) {
            job.cancel()
        }
    }
}
