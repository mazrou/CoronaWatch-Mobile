package solutus.coronawatch.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.coronawatch_mobile.R
import com.google.android.material.bottomnavigation.BottomNavigationView
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
                             replaceFragment(NouveltiesFragment())
                          return@OnNavigationItemSelectedListener true
                      }
                      R.id.newsFragment -> {
                              replaceFragment(NewsFragment())
                          return@OnNavigationItemSelectedListener true
                      }
                      R.id.videosFragment -> {
                               replaceFragment(VideosFragment())
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
        replaceFragment(NewsFragment())
    }

    /*
    * This function replace the fragment into the FrameLayout with the id 'nav_home_fragment' */
    private fun replaceFragment (fragment: Fragment){
        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_home_fragment , fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
