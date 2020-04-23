package solutus.coronawatch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.coronawatch_mobile.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comment_section.view.*
import solutus.coronawatch.data.db.entity.Comment


class CommentAdapter(context: Context, private var listCommentAdapter: List<Comment>) :
    BaseAdapter() {

    var context: Context? = context


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.comment_section, null)
        val comment = listCommentAdapter[position]
        //set comment
        view.comment.text = comment.text
        //set user name
        view.comment_user.text= comment.publisher.userName
        //set user avatar
        Picasso.get().load(comment.publisher.avatar).into(view.user_avatar)
        return view
    }

    override fun getItem(position: Int): Any {
        return listCommentAdapter[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listCommentAdapter.size
    }
}