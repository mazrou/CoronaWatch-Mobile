package solutus.coronawatch.ui.mainActivity.user.fragment.content.view.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.list_videos_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import solutus.coronawatch.data.entity.Post
import solutus.coronawatch.data.entity.Video
import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.reposetory.implementation.ContentRepository
import solutus.coronawatch.ui.mainActivity.MainActivity
import solutus.coronawatch.ui.mainActivity.MainActivity.Companion.replaceFragment
import solutus.coronawatch.ui.mainActivity.home.adapter.UserVideoAdapter
import solutus.coronawatch.ui.mainActivity.home.videos.VideoViewModelFactory
import solutus.coronawatch.ui.mainActivity.home.videos.VideosViewModel
import solutus.coronawatch.utilities.InjectorUtils


class UserListVideosFragment : Fragment() {

    companion object {
        fun newInstance() = UserListVideosFragment()
    }

    private lateinit var activity: MainActivity
    private lateinit var viewModelFactory: VideoViewModelFactory
    private lateinit var viewModel: VideosViewModel
    private val contentRepository =
        ContentRepository(
            ContentApi.invoke()
        )
    private lateinit var posts: ArrayList<Post>
    private lateinit var status: TextView

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
        val adapter = UserVideoAdapter(activity)
        recyclerView.adapter = adapter

        //set ViewModel
        viewModelFactory = (InjectorUtils.provideVideosViewModelFactory())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(VideosViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val posts = contentRepository.getUserPosts(activity.token)
            if (posts != null) {

                viewModel.getUserVideos(posts, activity.user)
            }
        }

        viewModel.userVideos.observe(
            viewLifecycleOwner,
            Observer { videos -> adapter.setVideos(videos as List<Video>) })
        //go to view Video on thumbnail click
        adapter.setOnItemClickListener(object : UserVideoAdapter.OnItemClickListener {
            override fun onItemClick(video: Video) {
                val viewVideoFragment = UserViewVideoFragment()
                val bundle = Bundle()
                bundle.putString("url", video.url)
                bundle.putString("content", video.content)
                bundle.putString("title", video.title)
                //bundle.putString("listComment", jsonList.toString())
                viewVideoFragment.arguments = bundle
                replaceFragment(activity, R.id.video_fragment, viewVideoFragment)
                status = view!!.findViewById(R.id.status_text)
            }
        })
        adapter.setOnItemLongClickListener(object : UserVideoAdapter.OnItemLongClickListener {
            override fun onItemLongClick(video: Video): Boolean {
                showDialog(activity.token, (video.id).toInt())
                return true
            }
        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    private fun showDialog(token: String, id: Int) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.costume_dialog_update_delete)
        val yesBtn = dialog.findViewById(R.id.updateBtn) as Button
        val noBtn = dialog.findViewById(R.id.deleteBtn) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(context, "update", Toast.LENGTH_LONG).show()
        }
        noBtn.setOnClickListener {
            CoroutineScope(IO).launch {
                contentRepository.deletePost(token, id)
                dialog.dismiss()
                /*  var posts = contentRepository.getUserPosts(activity.token)
                if (posts != null) {
                    viewModel.getUserVideos(posts, activity.user)
                }*/
            }
        }
        dialog.show()

    }
}
