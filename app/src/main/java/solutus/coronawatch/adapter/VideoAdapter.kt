package solutus.coronawatch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coronawatch_mobile.R
import com.example.coronawatch_mobile.databinding.UserVideoRecyclerviewBinding
import solutus.coronawatch.data.db.entity.Video


class VideoAdapter (
    private val videoList : List<Video>
): RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoAdapter.VideoViewHolder
            = VideoViewHolder(DataBindingUtil.inflate(
                   LayoutInflater.from(parent.context), R.layout.user_video_recyclerview, parent, false))


    override fun getItemCount() = videoList.size

    override fun onBindViewHolder(holder: VideoAdapter.VideoViewHolder, position: Int) {
        holder.videoRecyclerviewBinding.video = videoList[position]
      // I don't know how to put the videos yet 
        val videoUrl = videoList[position].url
        val youtubeUrl= "https://www.youtube.com/watch?v=$videoUrl"
        holder.videoRecyclerviewBinding.videoView.loadUrl(youtubeUrl)
        holder.videoRecyclerviewBinding.videoView.settings.javaScriptEnabled = true



    }

    inner class VideoViewHolder(
        val videoRecyclerviewBinding : UserVideoRecyclerviewBinding
    )  :
        RecyclerView.ViewHolder(videoRecyclerviewBinding.root)
}