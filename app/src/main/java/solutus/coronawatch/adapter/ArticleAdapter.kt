package solutus.coronawatch.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.coronawatch_mobile.R
import com.squareup.picasso.Picasso
import solutus.coronawatch.data.db.entity.Article


class ArticleAdapter(val context: Context) : RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {
    private var articles: List<Article> = ArrayList()
    private var listener: OnItemClickListener? = null
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ArticleHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ArticleHolder(itemView)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onBindViewHolder(@NonNull holder: ArticleHolder, position: Int) {
        val currentArticle: Article = articles[position]
        holder.textViewRedacterName.text = currentArticle.publisher.userName
        Picasso.get().load(currentArticle.publisher.avatar).into(holder.imageViewRedacterAvatar)
        holder.textViewArticleTitle.text = currentArticle.title
        //set the article content in the web view
        if (currentArticle.articleContent!= null) {
            holder.webViewArticleFile.webViewClient = object : WebViewClient() {

                override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {

                    super.onPageStarted(view, url, favicon)
                    view.visibility = View.GONE
                    holder.progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    view.visibility = View.VISIBLE
                    holder.progressBar.visibility = View.GONE
                }

            }
          holder.webViewArticleFile.loadUrl(currentArticle.articleContent)
        } else {
            holder.webViewArticleFile.visibility = View.GONE
            holder.progressBar.visibility = View.GONE
        }
        holder.webViewArticleText.loadUrl(currentArticle.url)
    }

    override fun getItemCount(): Int {
        return articles.size

    }

    fun setArticles(articles: List<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    fun getArticleAt(position: Int): Article? {
        return articles[position]
    }


    inner class ArticleHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal val textViewRedacterName: TextView = itemView.findViewById(R.id.redacter_name)
        internal val imageViewRedacterAvatar: de.hdodenhof.circleimageview.CircleImageView =
            itemView.findViewById(R.id.redacter_avatar)
        internal val textViewArticleTitle: TextView = itemView.findViewById(R.id.article_title)
        internal val webViewArticleFile: WebView =
            itemView.findViewById(R.id.article_file_content)
        internal val webViewArticleText: WebView =
            itemView.findViewById(R.id.article_text_content)
        private val textViewComment: TextView = itemView.findViewById(R.id.view_comment)
        internal val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)


        init {
            textViewComment.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener!!.onCommentEditClick(articles[position])
                }
            }
        }

    }

    interface OnItemClickListener {
        fun onCommentEditClick(article: Article)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

}
