package solutus.coronawatch.ui.home.videos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.coronawatch_mobile.R
import com.example.coronawatch_mobile.databinding.VideoRecyclerviewBinding
import solutus.coronawatch.data.db.entity.Video


class VideoAdapter (
    private val videoList : List<Video>,
    private val context :Context
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

     //   val thumbnail =   holder.view.findViewById<ImageView>(R.id.video_thumbnil)

      /*  Glide.with(context)
            .load(videoList[position].thumbnil)
            .into(thumbnail) */

      /*  holder.requestManager
            .load(videoList[position].thumbnil)
            .into(thumbnail) */


        // I don't know how to put the videos yet
      /*  val videoUrl = videoList[position].url
        val youtubeUrl= "https://www.youtube.com/watch?v=$videoUrl"
        holder.videoRecyclerviewBinding.videoView.loadUrl(youtubeUrl)
        holder.videoRecyclerviewBinding.videoView.settings.javaScriptEnabled = true*/



    }

    inner class VideoViewHolder(
        val videoRecyclerviewBinding : VideoRecyclerviewBinding
    )  :
        RecyclerView.ViewHolder(videoRecyclerviewBinding.root)

}