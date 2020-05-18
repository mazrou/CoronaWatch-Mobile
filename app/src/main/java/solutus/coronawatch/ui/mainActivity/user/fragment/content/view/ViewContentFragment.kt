package solutus.coronawatch.ui.mainActivity.user.fragment.content.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coronawatch_mobile.R
import solutus.coronawatch.ui.mainActivity.MainActivity.Companion.replaceFragment
import solutus.coronawatch.ui.mainActivity.user.fragment.content.view.fragments.UserListVideosFragment


class ViewContentFragment : Fragment() {

    companion object {
        fun newInstance() =
            ViewContentFragment()
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        replaceFragment(activity,R.id.video_fragment,UserListVideosFragment())
    }

}