package solutus.coronawatch.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.user_fragment.*
import solutus.coronawatch.ui.user.fragment.content.add.AddContentFragment
import solutus.coronawatch.ui.user.fragment.content.view.ViewContentFragment
import solutus.coronawatch.ui.user.fragment.profile.ProfileFragment


@Suppress("DEPRECATION")
class UserFragment : Fragment() {

    companion object {
        fun newInstance() = UserFragment()
        fun getPhotoProfile(): Int {//A changer par l'url de la photo profile
            return R.drawable.profile_image
        }
    }
    private lateinit var photoProfile:de.hdodenhof.circleimageview.CircleImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //set the profile image from the server
        val avatar = activity?.findViewById<View>(R.id.profile_image) as de.hdodenhof.circleimageview.CircleImageView
        // a programmer ulterieurement   avatar.setImageResource(url)


        val leftArc = view?.findViewById<View>(R.id.arc_left) as ImageView
        val rightArc = view?.findViewById<View>(R.id.arc_right) as ImageView
        val bottomArc = view?.findViewById<View>(R.id.arc_under) as ImageView
        //set ViewContentFragment the default fragment
        replaceFragment(ViewContentFragment())
        leftArc.visibility = View.VISIBLE
        rightArc.visibility = View.INVISIBLE
        bottomArc.visibility= View.INVISIBLE
        //navigation entre les fragments

        val viewContent = view?.findViewById<View>(R.id.view_content) as ImageView
        val addContent = view?.findViewById<View>(R.id.add_content) as ImageView
        val editProfile = view?.findViewById<View>(R.id.edit_profile) as ImageView



        viewContent.setOnClickListener {
            replaceFragment(ViewContentFragment())
            leftArc.visibility = View.VISIBLE
            rightArc.visibility = View.INVISIBLE
            bottomArc.visibility= View.INVISIBLE
        }
        addContent.setOnClickListener {
            replaceFragment(AddContentFragment())
            leftArc.visibility = View.INVISIBLE
            rightArc.visibility = View.INVISIBLE
            bottomArc.visibility= View.VISIBLE
        }
        editProfile.setOnClickListener {
            replaceFragment(ProfileFragment())
            leftArc.visibility = View.INVISIBLE
            rightArc.visibility = View.VISIBLE
            bottomArc.visibility= View.INVISIBLE
        }

       //set photo profile
        photoProfile = activity?.findViewById<View>(R.id.profile_image) as de.hdodenhof.circleimageview.CircleImageView
        photoProfile.setImageResource(getPhotoProfile())


    }

    private fun replaceFragment (fragment: Fragment){
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.user_nav_host_fragment , fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }


}
