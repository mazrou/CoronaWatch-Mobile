package solutus.coronawatch.ui.user.fragment.content.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.view_content_fragment.*
import solutus.coronawatch.adapter.VideoAdapter
import solutus.coronawatch.utilities.InjectorUtils

class ViewContentFragment : Fragment() {

    companion object {
        fun newInstance() =
            ViewContentFragment()
    }

    private lateinit var viewModel: ViewContentViewModel
    private lateinit var viewModelFactory: ViewContentViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeUi()

    }
    private fun initializeUi(){
        viewModelFactory = InjectorUtils.provideVideosViewModelFactory()
        viewModel=ViewModelProviders.of(this ,viewModelFactory ).get(ViewContentViewModel::class.java)
        viewModel.getVideos()

        viewModel.videos.observe(viewLifecycleOwner, Observer {videos->
            group_loading.visibility = View.GONE
            recyclerView_videos.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = VideoAdapter(videos)


            }
        })


    }

}