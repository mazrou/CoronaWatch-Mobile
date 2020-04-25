package solutus.coronawatch.adapter

import org.junit.Test

import org.junit.Assert.*
import solutus.coronawatch.data.db.entity.Comment
import solutus.coronawatch.data.db.entity.User
import android.content.Context

class CommentAdapterTest {

    @Test
    fun getContext() {

    }

    @Test
    fun setContext() {
    }

    @Test
    fun getView() {
    }

    @Test
    fun getItem() {
     val listComment : ArrayList<Comment> = arrayListOf(
         Comment(
              User(1,"userName1","email1","first1","last1","01-01-2020","url1"),
             "comment1"),
         Comment(
             User(2,"userName2","email2","first2","last2","01-01-2020","url2"),
             "comment1"))
        //val commentadapter = CommentAdapter(,listComment)
        //assertSame(commentadapter.getItem(0),listComment[0])
    }

    @Test
    fun getItemId() {
    }

    @Test
    fun getCount() {
    }
}