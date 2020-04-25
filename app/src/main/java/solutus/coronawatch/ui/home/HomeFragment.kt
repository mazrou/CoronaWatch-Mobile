package solutus.coronawatch.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.coronawatch_mobile.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import solutus.coronawatch.ui.activity.MainActivity.Companion.replaceFragment
import solutus.coronawatch.ui.home.news.NewsFragment
import solutus.coronawatch.ui.home.novelties.NouveltiesFragment
import solutus.coronawatch.ui.home.videos.VideosFragment


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

     private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        item -> when (item?.itemId) {
                      R.id.noveltiesFragment -> {
                             replaceFragment(activity,R.id.nav_home_fragment,NouveltiesFragment())
                          return@OnNavigationItemSelectedListener true
                      }
                      R.id.newsFragment -> {
                              replaceFragment(activity,R.id.nav_home_fragment,NewsFragment())
                          return@OnNavigationItemSelectedListener true
                      }
                      R.id.videosFragment -> {
                               replaceFragment(activity,R.id.nav_home_fragment,VideosFragment())
                          return@OnNavigationItemSelectedListener true
                      }
                 }
        return@OnNavigationItemSelectedListener false
    }

   override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view!!, savedInstanceState)
       val navigation = activity!!.findViewById<View>(R.id.homeNavigation) as BottomNavigationView
       navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // put the News Fragment as default fragment
        replaceFragment(activity,R.id.nav_home_fragment,NewsFragment())
    }


}
