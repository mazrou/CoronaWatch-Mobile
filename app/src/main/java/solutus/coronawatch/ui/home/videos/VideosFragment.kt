package solutus.coronawatch.ui.home.videos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.coronawatch_mobile.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.videos_fragment.*
import solutus.coronawatch.utilities.InjectorUtils

class VideosFragment : Fragment() {


    companion object {
        fun newInstance() = VideosFragment()
    }

    private lateinit var viewModelFactory: VideosViewModelFactory

    private lateinit var viewModel: VideosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.videos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeUi()
    }

    private fun initializeUi(){
        viewModelFactory = InjectorUtils.provideVideosViewModelFactory()
        viewModel=ViewModelProviders.of(this ,viewModelFactory ).get(VideosViewModel::class.java)
        viewModel.getVideos()

        viewModel.videos.observe(viewLifecycleOwner, Observer {videos->
            recyclerView_videos.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = VideoAdapter(videos)
            }
        })

     //   val groupeAdapter = GroupAdapter<GroupieViewHolder>()
     //   groupeAdapter.add(VideoAdapter())

     //   print("hadi hiosakdlfmfd ${viewModel.getVideos().toString()}")


    }

}
