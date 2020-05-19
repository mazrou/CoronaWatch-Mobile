package solutus.coronawatch.ui.mainActivity.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.coronawatch_mobile.R
import solutus.coronawatch.data.entity.Comment


class CommentAdapter(val context: Context) : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {
    private var comments: List<Comment> = ArrayList()
    private var listener: OnItemClickListener? = null
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): CommentHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_section, parent, false)
        return CommentHolder(itemView)
    }

    override fun onBindViewHolder(@NonNull holder: CommentHolder, position: Int) {
        val currentComment: Comment = comments[position]
        //set Comment text
        holder.commentView.text = currentComment.text
        //set user name
        holder.commentUserName.text = currentComment.user
        //set user avatar
        //Picasso.get().load(currentComment.user.image).into(holder.commentUserAvatar)

    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun setComments(comments: List<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    fun getCommentAt(position: Int): Comment? {
        return comments[position]
    }


    inner class CommentHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal val commentView: TextView = itemView.findViewById(R.id.comment)
        internal val commentUserName: TextView = itemView.findViewById(R.id.comment_user)
        internal val commentUserAvatar: de.hdodenhof.circleimageview.CircleImageView =
            itemView.findViewById(R.id.user_avatar)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(comments[position])
                }
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(comment: Comment)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

}