package solutus.coronawatch.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.coronawatch_mobile.R.layout.user_video_section
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_video_section.view.*
import solutus.coronawatch.data.db.entity.Video


class UserVideoAdapter(context: Context, private var listVideoAdapter: List<Video>) :
    BaseAdapter() {

    var context: Context?= context


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view=inflater.inflate(user_video_section,null)
        val video=listVideoAdapter[position]
        //set video title
        view.video_title.text = video.title
        //set video thumnnil
        Picasso.get().load(video.thumbnil).into(view.videoThumbnil)
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