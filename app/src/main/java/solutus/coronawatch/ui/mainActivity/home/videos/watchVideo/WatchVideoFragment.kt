package solutus.coronawatch.ui.mainActivity.home.videos.watchVideo

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.watch_video_fragment.*


class WatchVideoFragment : Fragment() {

    companion object {
        fun newInstance() =
            WatchVideoFragment()
    }

    private var url: String? = null
    private var title: String? = null
    private var content: String? = null
    private lateinit var mediaController: MediaController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.watch_video_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //get data from list videos fragment using bundle
        val bundle = arguments
        url = bundle!!.getString("url")
        content = bundle.getString("content")
        title = bundle.getString("title")


        //set video
        setVideo()

        //set title
        title_text_view.text = title

        //set content
        content_text_view.text = content
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setVideo() {
        //set media controller
        mediaController = MediaController(context)
        video_view.setMediaController(mediaController)
        mediaController.setAnchorView(video_view)
        // set progress bar
        video_view.setOnPreparedListener { mp ->
            mp.start()
            mp.setOnVideoSizeChangedListener { mp, _, _ ->
                video_progress.visibility = View.GONE
                mp.start()
            }
        }
        video_view.setVideoURI(Uri.parse(url))
    }

}
