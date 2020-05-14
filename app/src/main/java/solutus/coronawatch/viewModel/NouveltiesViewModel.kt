package solutus.coronawatch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import solutus.coronawatch.data.db.entity.Article
import solutus.coronawatch.data.reposetory.NouveltiesRepository
import solutus.coronawatch.utilities.Coroutines

open class NouveltiesViewModel(
    private val articlesRepository: NouveltiesRepository
) : ViewModel() {

    private lateinit var job : Job
    private val _articles = MutableLiveData<List<Article>>()

    val articles : LiveData<List<Article>>
        get() = _articles


    fun getArticles()  {
        job = Coroutines.ioThMain(
            {articlesRepository.getArticle()},
            {_articles.value = it}
        )
    }


    override fun onCleared() {
        if(::job.isInitialized){
            job.cancel()
        }
    }
}
