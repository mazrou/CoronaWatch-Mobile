package solutus.coronawatch.ui.home.videos.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.Observer

import com.example.coronawatch_mobile.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.list_videos_fragment.*
import solutus.coronawatch.adapter.VideoAdapter
import solutus.coronawatch.data.db.entity.Comment
import solutus.coronawatch.data.db.entity.Video
import solutus.coronawatch.factory.VideoViewModelFactory
import solutus.coronawatch.ui.MainActivity.Companion.replaceFragment
import solutus.coronawatch.utilities.InjectorUtils
import solutus.coronawatch.viewModel.VideosViewModel

class ListVideosFragment : Fragment() {

    companion object {
        fun newInstance() = ListVideosFragment()
    }
    private lateinit var viewModelFactory: VideoViewModelFactory
    private lateinit var viewModel: VideosViewModel

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
    private fun initializeUi(){
        viewModelFactory = (InjectorUtils.provideVideosViewModelFactory())
        viewModel= ViewModelProviders.of(this ,viewModelFactory ).get(VideosViewModel::class.java)
        viewModel.getVideos()
        viewModel.videos.observe(viewLifecycleOwner, Observer { videos ->
            list_video.also {
                it.adapter = VideoAdapter(context!!, videos)
                it.onItemClickListener =
                    AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                        val viewVideoFragment = ViewVideoFragment()
                        val video: Video = it.adapter.getItem(position) as Video
                        val comments = video.comments
                        //convert list comments to json
                        val gson = Gson()
                        val arrayCommentType = object : TypeToken<ArrayList<Comment>>() {}.type
                        val jsonList = gson.toJson(video.comments,arrayCommentType)
                        /*************/
                        val bundle = Bundle()
                        bundle.putString("url", video.url)
                        bundle.putString("listComment", jsonList.toString())
                        viewVideoFragment.arguments = bundle
                        replaceFragment(activity,R.id.video_fragment,viewVideoFragment)
                    }
            }
        })
    }



}
