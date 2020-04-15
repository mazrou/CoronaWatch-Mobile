package solutus.coronawatch.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
            return R.mipmap.profile_image
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
        //set photo profile
        profile_image.setImageResource(getPhotoProfile())
        //set ViewContentFragment the default fragment
        replaceFragment(ViewContentFragment())
        arc_left.visibility = View.VISIBLE
        arc_right.visibility = View.INVISIBLE
        arc_under.visibility= View.INVISIBLE
        //navigation entre les fragments

        view_content.setOnClickListener {
            replaceFragment(ViewContentFragment())
            arc_left.visibility = View.VISIBLE
            arc_right.visibility = View.INVISIBLE
            arc_under.visibility= View.INVISIBLE
        }
        add_content.setOnClickListener {
            replaceFragment(AddContentFragment())
            arc_left.visibility = View.INVISIBLE
            arc_right.visibility = View.INVISIBLE
            arc_under.visibility= View.VISIBLE
        }
        edit_profile.setOnClickListener {
            replaceFragment(ProfileFragment())
            arc_left.visibility = View.INVISIBLE
            arc_right.visibility = View.VISIBLE
            arc_under.visibility= View.INVISIBLE
        }
    }
    private fun replaceFragment (fragment: Fragment){
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.user_nav_host_fragment , fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }
}
