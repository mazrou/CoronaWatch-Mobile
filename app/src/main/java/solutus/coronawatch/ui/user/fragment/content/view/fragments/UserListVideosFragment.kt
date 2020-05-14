package solutus.coronawatch.ui.user.fragment.content.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.coronawatch_mobile.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.list_videos_fragment.*
import solutus.coronawatch.adapter.UserVideoAdapter
import solutus.coronawatch.data.db.entity.Comment
import solutus.coronawatch.data.db.entity.Video
import solutus.coronawatch.factory.VideoViewModelFactory
import solutus.coronawatch.ui.activity.MainActivity.Companion.replaceFragment
import solutus.coronawatch.utilities.VideoInjectorUtils
import solutus.coronawatch.viewModel.VideosViewModel


class UserListVideosFragment : Fragment() {

    companion object {
        fun newInstance() = UserListVideosFragment()
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
        viewModelFactory = (VideoInjectorUtils.provideVideosViewModelFactory())
        viewModel= ViewModelProviders.of(this ,viewModelFactory ).get(VideosViewModel::class.java)
        viewModel.getUserVideos()
        viewModel.userVideos.observe(viewLifecycleOwner, Observer { videos ->
            list_video.also {
                it.adapter = UserVideoAdapter(context!!, videos)
                it.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, position, _ ->
                        val viewVideoFragment = UserViewVideoFragment()
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
