package solutus.coronawatch.ui.mainActivity.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coronawatch_mobile.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_fragment.*
import solutus.coronawatch.ui.mainActivity.MainActivity
import solutus.coronawatch.ui.mainActivity.MainActivity.Companion.replaceFragment
import solutus.coronawatch.ui.mainActivity.user.fragment.content.add.AddContentFragment
import solutus.coronawatch.ui.mainActivity.user.fragment.content.view.ViewContentFragment
import solutus.coronawatch.ui.mainActivity.user.fragment.profile.ProfileFragment



class UserFragment : Fragment() {
    private lateinit var activity : MainActivity
    private lateinit var avatar:de.hdodenhof.circleimageview.CircleImageView

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
        var user = activity.user
        //set photo profile
        avatar = activity?.findViewById<View>(R.id.profile_image) as de.hdodenhof.circleimageview.CircleImageView
        if (user.image != null){
            Picasso.get().load(user.image).into(avatar)
        }
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
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }


}
