package solutus.coronawatch.ui.home.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coronawatch_mobile.R
import solutus.coronawatch.factory.VideoViewModelFactory
import solutus.coronawatch.ui.activity.MainActivity.Companion.replaceFragment
import solutus.coronawatch.ui.home.videos.fragments.ListVideosFragment
import solutus.coronawatch.viewModel.VideosViewModel


class VideosFragment : Fragment() {


    companion object {
        fun newInstance() = VideosFragment()
    }

    private lateinit var viewModelFactory: VideoViewModelFactory

    private lateinit var viewModel: VideosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.videos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        replaceFragment(activity,R.id.video_fragment,ListVideosFragment())

    }


}

