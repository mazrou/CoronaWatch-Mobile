package solutus.coronawatch.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.MediaController
import com.squareup.picasso.Picasso
import solutus.coronawatch.data.db.entity.Video
import com.example.coronawatch_mobile.R.layout.video_section
import kotlinx.android.synthetic.main.user_video_section.view.*
import kotlinx.android.synthetic.main.video_section.view.*
import kotlinx.android.synthetic.main.video_section.view.videoThumbnil
import kotlinx.android.synthetic.main.video_section.view.video_title

class VideoAdapter(context: Context, private var listVideoAdapter: List<Video>) :
    BaseAdapter() {

    var context: Context?= context

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view=inflater.inflate(video_section,null)
        val video=listVideoAdapter[position]
        //set video title
        view.video_title.text = video.title
        //set video thumnnil
        Picasso.get().load(video.thumbnail).into(view.videoThumbnil)
        //set user name
        view.video_user.text = video.publisher.firstName + " "+ video.publisher.lastName
        //set user avatar
        Picasso.get().load(video.publisher.image).into(view.user_avatar)
       return view
    }

    override fun getItem(position: Int): Any {
        return listVideoAdapter[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listVideoAdapter.size
    }
}