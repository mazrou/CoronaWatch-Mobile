package solutus.coronawatch.ui.home.novelties

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.article_comments_fragment.*
import solutus.coronawatch.adapter.CommentAdapter
import solutus.coronawatch.factory.ArticleCommentsViewModelFactory
import solutus.coronawatch.viewModel.ArticleCommentsViewModel

class ArticleCommentsFragment : Fragment() {

    companion object {
        fun newInstance() = ArticleCommentsFragment()
    }

    private lateinit var viewModel: ArticleCommentsViewModel
    private lateinit var viewModelFactory: ArticleCommentsViewModelFactory
    private lateinit var comment: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.article_comments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //set ViewModel
        viewModelFactory = ArticleCommentsViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ArticleCommentsViewModel::class.java)
        val comments = viewModel.comments


        //set list comments on comment_list_view
        val adapter = CommentAdapter(activity!!, comments)
        list_comment.adapter = adapter

        edit_comment.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                comment_image.visibility = View.GONE
                done.visibility = View.VISIBLE
                edit_comment.requestFocus()
                edit_comment.isFocusableInTouchMode = true

                val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(
                    edit_comment,
                    InputMethodManager.SHOW_IMPLICIT
                )
                return@OnTouchListener true
            }
            false
        })

        edit_comment.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                //hide keyboard
                val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)
                true
            } else {
                false
            }
        }
        done.setOnClickListener {
            if (edit_comment.text.toString() == "") {
                Toast.makeText(activity, "اكتب تعليقا", Toast.LENGTH_SHORT).show()

            } else {
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


