package solutus.coronawatch.ui.mainActivity.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.coronawatch_mobile.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_fragment.*
import solutus.coronawatch.ui.mainActivity.MainActivity


class UserFragment : Fragment() {
    private lateinit var activity : MainActivity
    private lateinit var avatar:de.hdodenhof.circleimageview.CircleImageView
    lateinit var navController: NavController

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
        val user = activity.user
        //set photo profile
        avatar =
            activity.findViewById<View>(R.id.profile_image) as de.hdodenhof.circleimageview.CircleImageView
        Picasso.get().load(user.image).into(avatar)

        //set ViewContentFragment the default fragment
        arc_left.visibility = View.VISIBLE
        arc_right.visibility = View.INVISIBLE
        arc_under.visibility= View.INVISIBLE
        //navigation entre les fragments

        view_content.setOnClickListener {
            navController =
                Navigation.findNavController(requireActivity(), R.id.user_nav_host_fragment)
            navController.navigate(R.id.to_videos_fragment_action)
            arc_left.visibility = View.VISIBLE
            arc_right.visibility = View.INVISIBLE
            arc_under.visibility= View.INVISIBLE
        }
        add_content.setOnClickListener {
            navController =
                Navigation.findNavController(requireActivity(), R.id.user_nav_host_fragment)
            navController.navigate(R.id.to_add_video_fragment_action)
            arc_left.visibility = View.INVISIBLE
            arc_right.visibility = View.INVISIBLE
            arc_under.visibility= View.VISIBLE
        }
        edit_profile.setOnClickListener {
            navController =
                Navigation.findNavController(requireActivity(), R.id.user_nav_host_fragment)
            navController.navigate(R.id.to_profile_fragment_action)
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
