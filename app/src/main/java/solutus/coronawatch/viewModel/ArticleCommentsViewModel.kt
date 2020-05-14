package solutus.coronawatch.viewModel

import androidx.lifecycle.ViewModel
import solutus.coronawatch.data.db.entity.Article
import solutus.coronawatch.data.db.entity.Comment

class ArticleCommentsViewModel() : ViewModel() {
    companion object{
        var staticArticleComments   = ArrayList<Comment>()
    }
    val comments :List<Comment> = staticArticleComments

}