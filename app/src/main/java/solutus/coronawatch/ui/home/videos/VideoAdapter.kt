package solutus.coronawatch.ui.home.videos

import androidx.lifecycle.LiveData
import com.example.coronawatch_mobile.R
import com.example.coronawatch_mobile.databinding.VideoRecyclerviewBinding
import com.xwray.groupie.databinding.BindableItem
import solutus.coronawatch.data.entity.Video


class VideoItem (var video :LiveData<List<Video>>): BindableItem<VideoRecyclerviewBinding>()  {


    override fun getLayout() = R.layout.video_recyclerview

    override fun bind(viewBinding: VideoRecyclerviewBinding, position: Int) {
            viewBinding.video = video.value!![position]
    }
}