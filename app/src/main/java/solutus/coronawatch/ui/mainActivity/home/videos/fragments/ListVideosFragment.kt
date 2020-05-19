package solutus.coronawatch.ui.mainActivity.home.videos.fragments

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
import kotlinx.android.synthetic.main.list_videos_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import solutus.coronawatch.data.entity.Post
import solutus.coronawatch.data.entity.Video
import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.reposetory.implementation.ContentRepository
import solutus.coronawatch.ui.mainActivity.MainActivity.Companion.replaceFragment
import solutus.coronawatch.ui.mainActivity.home.adapter.VideoAdapter
import solutus.coronawatch.ui.mainActivity.home.videos.VideoViewModelFactory
import solutus.coronawatch.ui.mainActivity.home.videos.VideosViewModel
import solutus.coronawatch.utilities.InjectorUtils

class ListVideosFragment : Fragment() {

    companion object {
        fun newInstance() = ListVideosFragment()
    }

    private lateinit var viewModelFactory: VideoViewModelFactory
    private lateinit var viewModel: VideosViewModel
    private val contentRepository =
        ContentRepository(
            ContentApi()
        )
    private lateinit var posts: ArrayList<Post>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_videos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeUi()
    }

    private fun initializeUi() {

        //set recycle view adapter
        val recyclerView: RecyclerView = list_video as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        val adapter = VideoAdapter(activity!!)
        recyclerView.adapter = adapter

        //set ViewModel
        viewModelFactory = (InjectorUtils.provideVideosViewModelFactory())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(VideosViewModel::class.java)
        CoroutineScope(IO).launch {
            val posts = contentRepository.getPosts()
            if (posts != null) {

                viewModel.getVideos(posts)

            }
        }
        viewModel.videos.observe(
            viewLifecycleOwner,
            Observer { videos -> adapter.setVideos(videos as List<Video>) })
        //go to view Video on thumbnail click
        adapter.setOnItemClickListener(object : VideoAdapter.OnItemClickListener {
            override fun onItemClick(video: Video) {
                val viewVideoFragment = ViewVideoFragment()
                val bundle = Bundle()
                bundle.putString("url", video.url)
                bundle.putString("content", video.content)
                bundle.putString("title", video.title)
                //bundle.putString("listComment", jsonList.toString())
                viewVideoFragment.arguments = bundle
                replaceFragment(activity, R.id.video_fragment, viewVideoFragment)
            }
        })
    }


}



