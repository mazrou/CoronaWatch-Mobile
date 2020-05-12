package solutus.coronawatch.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coronawatch_mobile.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_fragment.*
import kotlinx.android.synthetic.main.video_section.view.*
import solutus.coronawatch.ui.MainActivity.Companion.replaceFragment
import solutus.coronawatch.ui.user.fragment.content.add.AddContentFragment
import solutus.coronawatch.ui.user.fragment.content.view.ViewContentFragment
import solutus.coronawatch.ui.user.fragment.profile.ProfileFragment



class UserFragment : Fragment() {

    companion object {
        fun newInstance() = UserFragment()
        //fun getPhotoProfile(): String  {
          // return user.avatar
        //}
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //set photo profile
        //Picasso.get().load(getPhotoProfile()).into(profile_image)
        //set ViewContentFragment the default fragment
        replaceFragment(activity,R.id.user_nav_host_fragment,ViewContentFragment())
        arc_left.visibility = View.VISIBLE
        arc_right.visibility = View.INVISIBLE
        arc_under.visibility= View.INVISIBLE
        //navigation entre les fragments

        view_content.setOnClickListener {
            replaceFragment(activity,R.id.user_nav_host_fragment,ViewContentFragment())
            arc_left.visibility = View.VISIBLE
            arc_right.visibility = View.INVISIBLE
            arc_under.visibility= View.INVISIBLE



        }
        add_content.setOnClickListener {
            replaceFragment(activity,R.id.user_nav_host_fragment,AddContentFragment())
            arc_left.visibility = View.INVISIBLE
            arc_right.visibility = View.INVISIBLE
            arc_under.visibility= View.VISIBLE
        }
        edit_profile.setOnClickListener {
            replaceFragment(activity,R.id.user_nav_host_fragment,ProfileFragment())
            arc_left.visibility = View.INVISIBLE
            arc_right.visibility = View.VISIBLE
            arc_under.visibility= View.INVISIBLE
        }
    }

}
