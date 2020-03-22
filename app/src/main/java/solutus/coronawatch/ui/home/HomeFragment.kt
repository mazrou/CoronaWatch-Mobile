package solutus.coronawatch.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.coronawatch_mobile.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.home_fragment.*
import solutus.coronawatch.ui.home.news.NewsFragment
import solutus.coronawatch.ui.home.novelties.NouveltiesFragment
import solutus.coronawatch.ui.home.videos.VideosFragment

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

     private val mOnNavigationItemReselectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
      //  homeNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemReselectedListener)
        replaceFragment(NewsFragment())
    }

   override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

       super.onCreateOptionsMenu(menu, inflater)
       // Put the menu
       inflater.inflate(R.menu.home_nav, menu)
   }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Switch between the fragment
        when (item.itemId) {
             R.id.noveltiesFragment -> {
                 replaceFragment(NouveltiesFragment())
             }
             R.id.newsFragment -> {
                 replaceFragment(NewsFragment())
             }
             R.id.videosFragment -> {
                 replaceFragment(VideosFragment())
             }
         }
        return super.onOptionsItemSelected(item)
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
