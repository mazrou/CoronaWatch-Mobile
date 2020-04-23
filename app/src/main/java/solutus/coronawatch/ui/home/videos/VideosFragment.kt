package solutus.coronawatch.ui.home.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.videos_fragment.*
import solutus.coronawatch.data.db.entity.Video
import solutus.coronawatch.ui.home.videos.adapter.VerticalSpacingItemDecorator
import solutus.coronawatch.ui.home.videos.adapter.VideoPlayerRecyclerAdapter
import solutus.coronawatch.utilities.InjectorUtils
import java.lang.Exception
import java.util.ArrayList


//@Suppress("PLUGIN_WARNING")
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
        //viewModel.getVideos()

        /*viewModel.videos.observe(viewLifecycleOwner, Observer {videos->
            group_loading.visibility = GONE

          try {
              recyclerView_videos.also {
                  it.layoutManager = LinearLayoutManager(requireContext())
                  it.setHasFixedSize(true)
                  it.adapter =
                      VideoPlayerRecyclerAdapter(
                          videos as ArrayList<Video>?,
                          initGlide()
                      )
                  it.addItemDecoration(
                      VerticalSpacingItemDecorator(
                          10
                      )
                  )


              }
          }catch (e :Exception){
              e.stackTrace
          }
        })

*/

        viewModel.getUser()
        viewModel.user.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(requireContext(),it.email,Toast.LENGTH_SHORT).show()
            print("jihiotlknj")
        })

    }

    private fun initGlide(): RequestManager? {
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
        return Glide.with(this)
            .setDefaultRequestOptions(options)
    }

}
