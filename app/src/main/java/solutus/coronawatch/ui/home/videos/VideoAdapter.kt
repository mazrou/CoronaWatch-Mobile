package solutus.coronawatch.ui.home.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coronawatch_mobile.R
import com.example.coronawatch_mobile.databinding.VideoRecyclerviewBinding
import solutus.coronawatch.data.entity.Video


class VideoAdapter (
    private val videoList : List<Video>
): RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoAdapter.VideoViewHolder
            = VideoViewHolder(DataBindingUtil
        .inflate(LayoutInflater
            .from(parent.context),
            R.layout.video_recyclerview,
            parent,
            false
        ))


    override fun getItemCount() = videoList.size

    override fun onBindViewHolder(holder: VideoAdapter.VideoViewHolder, position: Int) {
        holder.videoRecyclerviewBinding.video = videoList[position]
    }

    inner class VideoViewHolder(
        val videoRecyclerviewBinding : VideoRecyclerviewBinding
    )  :
        RecyclerView.ViewHolder(videoRecyclerviewBinding.root)
}