package solutus.coronawatch.ui.home.novelties

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.nouvelties_fragment.*
import solutus.coronawatch.adapter.ArticleAdapter
import solutus.coronawatch.data.db.entity.Article
import solutus.coronawatch.data.db.entity.Comment
import solutus.coronawatch.factory.NouveltiesViewModelFactory
import solutus.coronawatch.ui.activity.MainActivity.Companion.replaceFragment
import solutus.coronawatch.utilities.ArticleInjectorUtils
import solutus.coronawatch.viewModel.ArticleCommentsViewModel
import solutus.coronawatch.viewModel.NouveltiesViewModel


@Suppress("CAST_NEVER_SUCCEEDS")
class NouveltiesFragment : Fragment() {

    companion object {
        fun newInstance() = NouveltiesFragment()
    }

    private lateinit var viewModelFactory: NouveltiesViewModelFactory
    private lateinit var viewModel: NouveltiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nouvelties_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //set recycle view adapter
        val recyclerView: RecyclerView = recycler_view as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        val adapter = ArticleAdapter(activity!!)
        recyclerView.adapter = adapter

        //set ViewModel
        viewModelFactory = (ArticleInjectorUtils.provideArticleViewModelFactory())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NouveltiesViewModel::class.java)
        viewModel.getArticles()
        viewModel.articles.observe(
            viewLifecycleOwner,
            Observer { articles -> adapter.setArticles(articles as List<Article>) })
        adapter.setOnItemClickListener(object : ArticleAdapter.OnItemClickListener {
            override fun onCommentEditClick(article: Article) {
                ArticleCommentsViewModel.staticArticleComments = article.comments as ArrayList<Comment>
                replaceFragment(activity,R.id.nav_home_fragment,ArticleCommentsFragment())
            }
        })

    }

}
