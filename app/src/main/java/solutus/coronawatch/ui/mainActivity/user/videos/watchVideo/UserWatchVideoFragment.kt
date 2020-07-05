package solutus.coronawatch.ui.mainActivity.user.videos.watchVideo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.watch_video_fragment.*


class UserWatchVideoFragment : Fragment() {

    companion object {
        fun newInstance() =
            UserWatchVideoFragment()
    }

    private var url: String? = null
    private var title: String? = null
    private var content: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.watch_video_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ///get data from list videos fragment using bundle
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
        /* video_view.webViewClient = WebViewClient()
         video_view.webChromeClient = ChromeClient(requireActivity())
         val webSettings: WebSettings = video_view.settings

         webSettings.javaScriptEnabled = true
         webSettings.allowFileAccess = true
         webSettings.setAppCacheEnabled(true)

         video_view.loadUrl(url)*/
    }

}
