package solutus.coronawatch.ui.mainActivity.user.fragment.content.view.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.coronawatch_mobile.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.list_videos_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import solutus.coronawatch.ui.mainActivity.home.adapter.UserVideoAdapter
import solutus.coronawatch.data.db.entity.Post
import solutus.coronawatch.data.db.entity.Video
import solutus.coronawatch.data.internal.NoConnectivityException
import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.reposetory.implementation.ContentRepository
import solutus.coronawatch.ui.mainActivity.home.videos.VideoViewModelFactory
import solutus.coronawatch.ui.mainActivity.MainActivity
import solutus.coronawatch.ui.mainActivity.MainActivity.Companion.replaceFragment
import solutus.coronawatch.utilities.InjectorUtils
import solutus.coronawatch.ui.mainActivity.home.videos.VideosViewModel


class UserListVideosFragment : Fragment()  , KodeinAware{

    companion object {
        fun newInstance() = UserListVideosFragment()
    }

    override val kodein by closestKodein()
    private lateinit var activity : MainActivity
    private lateinit var viewModelFactory: VideoViewModelFactory
    private lateinit var viewModel: VideosViewModel
    private val contentRepository  : ContentRepository by instance()


    private lateinit var posts : ArrayList<Post>
    private lateinit var status : TextView

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

        viewModelFactory = (InjectorUtils.provideVideosViewModelFactory(requireContext()))
        viewModel= ViewModelProviders.of(this ,viewModelFactory ).get(VideosViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            var posts = contentRepository.getUserPosts(activity.token)
            if (posts != null) {

                try {
                    viewModel.getUserVideos(posts,activity.user)
                }catch (e : NoConnectivityException){
                    Toast.makeText(requireContext() , "makach connextion ", Toast.LENGTH_LONG).show()
                }



            }
        }
        viewModel.userVideos.observe(viewLifecycleOwner, Observer { videos ->
            list_video.also {
                it.adapter = UserVideoAdapter(context!!, videos)
                it.onItemClickListener =
                    AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                        val viewVideoFragment = UserViewVideoFragment()
                        val video: Video = it.adapter.getItem(position) as Video
                        //val comments = video.comments
                        //convert list comments to json
                        val gson = Gson()
                        //val arrayCommentType = object : TypeToken<ArrayList<Comment>>() {}.type
                        //val jsonList = gson.toJson(video.comments,arrayCommentType)
                        /*************/
                        val bundle = Bundle()
                        bundle.putString("url", video.url)
                        bundle.putString("content", video.content)
                        bundle.putString("title",video.title)
                        //bundle.putString("listComment", jsonList.toString())
                        viewVideoFragment.arguments = bundle
                        replaceFragment(activity,R.id.video_fragment,viewVideoFragment)
                        status = view!!.findViewById(R.id.status_text)

                    }

                it.onItemLongClickListener = AdapterView.OnItemLongClickListener{ arg0,arg1, position, arg3 ->
                    val video: Video = it.adapter.getItem(position) as Video
                    showDialog(activity.token, (video.id).toInt())
                    return@OnItemLongClickListener true
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }
    private fun showDialog(token : String ,  id : Int) {
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
