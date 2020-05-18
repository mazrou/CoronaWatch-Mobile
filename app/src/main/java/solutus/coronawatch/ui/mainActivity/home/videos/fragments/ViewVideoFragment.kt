package solutus.coronawatch.ui.mainActivity.home.videos.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.MediaController
import android.widget.Toast

import com.example.coronawatch_mobile.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.view_video_fragment.*
import solutus.coronawatch.data.db.entity.Comment

class ViewVideoFragment : Fragment() {



    companion object {
        fun newInstance() = ViewVideoFragment()
    }
    private lateinit var comment: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_video_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val gson = Gson()
        val arrayCommentType = object : TypeToken<ArrayList<Comment>>() {}.type
        val bundle = arguments

        var videoUrl = bundle!!.getString("url")
        val url : Uri = Uri.parse(videoUrl)

        val content = bundle!!.getString("content")

        val title = bundle!!.getString("title")

       // val jsonList = bundle.getString("listComment")
        //val listComment :  List<Comment> = gson.fromJson(jsonList,arrayCommentType)

        //show comments
        //val adpter = CommentAdapter(context!!,listComment)
        //list_comment.adapter = adpter

        //set video url
        videoView.setVideoURI(url)
        //set media controleur
        title_text_view.setText(title)
        content_text_view.setText(content)



        val mediaController = MediaController(context)
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)
        // set progress bar
        videoView.setOnPreparedListener { mp ->
            mp.start()
            mp.setOnVideoSizeChangedListener { mp, _, _ ->
                progressBar_loading.visibility = View.GONE
                mp.start()
            }
        }

        edit_comment.setOnEditorActionListener { _, actionId,_ ->
            comment_image.visibility = View.GONE
            done.visibility = View.VISIBLE
            if(actionId == EditorInfo.IME_ACTION_GO){
                //hide keyboard
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)
                true
            }else{
                false
            }

        }
        done.setOnClickListener {
            if(edit_comment.text.toString() == ""){
                Toast.makeText(activity,"اكتب تعليقا", Toast.LENGTH_SHORT).show()

            }else{
                comment = edit_comment.text.toString()
                edit_comment.text.clear()
                Toast.makeText(activity, "لقد علقت ب $comment", Toast.LENGTH_SHORT).show()
                uploadComment()
            }
            comment_image.visibility = View.VISIBLE
            done.visibility = View.GONE
            edit_comment.isCursorVisible = false
        }
    }

    private fun uploadComment() {
        Toast.makeText(activity, "لقد علقت ب $comment", Toast.LENGTH_SHORT).show()
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
