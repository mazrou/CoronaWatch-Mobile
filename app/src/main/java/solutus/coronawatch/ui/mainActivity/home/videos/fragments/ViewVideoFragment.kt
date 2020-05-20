package solutus.coronawatch.ui.mainActivity.home.videos.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.MediaController
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.view_video_fragment.*

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
        //get data from list videos fragment uing bundle
        val bundle = arguments
        val videoUrl = bundle!!.getString("url")
        val url : Uri = Uri.parse(videoUrl)
        val content = bundle.getString("content")
        val title = bundle.getString("title")

        /*val gson = Gson()
        val arrayCommentType = object : TypeToken<ArrayList<Comment>>() {}.type
        val jsonList = bundle.getString("listComment")
        val listComment :  List<Comment> = gson.fromJson(jsonList,arrayCommentType)

        //show comments
        val adpter = CommentAdapter(context!!,listComment)
        list_comment.adapter = adpter*/

        //set video url
        videoView.setVideoURI(url)
        //set media controleur
        title_text_view.text = title
        content_text_view.text = content



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
