package solutus.coronawatch.ui.mainActivity.home.videos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.coronawatch_mobile.R
import com.squareup.picasso.Picasso
import solutus.coronawatch.data.entity.Comment

class CommentAdapter(val context: Context) : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {
    private var comments: List<Comment> = ArrayList()
    private var listener: OnItemClickListener? = null
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): CommentHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_item, parent, false)
        return CommentHolder(itemView)
    }

    override fun onBindViewHolder(@NonNull holder: CommentHolder, position: Int) {
        val currentComment: Comment = comments[position]
        //set comment
        holder.commentView.text = currentComment.content
        //set user avatar
        Picasso.get().load(currentComment.publisher.image).into(holder.videoUserAvatar)

    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun setVideos(comments: List<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }


    inner class CommentHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal val videoUserAvatar: de.hdodenhof.circleimageview.CircleImageView =
            itemView.findViewById(R.id.user_avatar)
        internal val commentView: TextView = itemView.findViewById(R.id.comment_view)
        private val reportButton: ImageView = itemView.findViewById(R.id.report_button)

        init {
            reportButton.setOnClickListener {
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
