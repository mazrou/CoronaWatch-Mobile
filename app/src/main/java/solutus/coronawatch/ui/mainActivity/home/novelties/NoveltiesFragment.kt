package solutus.coronawatch.ui.mainActivity.home.novelties

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
import solutus.coronawatch.data.entity.Article
import solutus.coronawatch.ui.mainActivity.home.novelties.adapter.ArticleAdapter
import solutus.coronawatch.utilities.InjectorUtils

class NoveltiesFragment : Fragment() {

    companion object {
        fun newInstance() = NoveltiesFragment()
    }

    private lateinit var viewModelFactory: NoveltiesViewModelFactory
    private lateinit var viewModel: NoveltiesViewModel
    private lateinit var adapter: ArticleAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nouvelties_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //set ViewModel
        viewModelFactory = (InjectorUtils.provideNoveltiesViewModelFactory())
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(NoveltiesViewModel::class.java)
        initializeUi()
    }

    private fun initializeUi() {
        //set recycle view adapter
        val recyclerView: RecyclerView = list_article as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        adapter = ArticleAdapter(requireActivity())
        recyclerView.adapter = adapter

        viewModel.getArticles()
        viewModel.articles.observe(
            viewLifecycleOwner,
            Observer { articles -> adapter.setArticles(articles as List<Article>) })
    }
}

