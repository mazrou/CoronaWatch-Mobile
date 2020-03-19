package solutus.coronawatch.context

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.fragment_video.*
import solutus.coronawatch.R
import solutus.coronawatch.adapter.VideoAdapter
import solutus.coronawatch.model.User
import solutus.coronawatch.model.Video

class MainActivity : AppCompatActivity() {
    //  private var videoList = ArrayList<Video> ()
    //private var adapter  = VideoAdapter(videoList )
     val manager = supportFragmentManager
    private val mOnNavigatoinItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_home -> {
                    println("i'm hme")
                    replaceFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.fragment_health -> {
                    println("i'm hme")
                    replaceFragment(HealthFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.fragment_info -> {
                    println("i'm hme")
                    replaceFragment(InfoFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.fragment_map -> {
                    println("i'm hme")
                    replaceFragment(MapFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.fragment_user -> {
                    println("i'm hme")
                    replaceFragment(UserFragment())
                    return@OnNavigationItemSelectedListener true
                }

                else -> return@OnNavigationItemSelectedListener false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigatoinItemSelectedListener)
        replaceFragment(UserFragment())


        // video_fragment.layoutManager = LinearLayoutManager(this)
        //   video_fragment.adapter = adapter

        ///  loadVideos()


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_fragment, fragment)
        fragmentTransaction.commit()

    }
}

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_navigation,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId ){
           R.id.nav_videos ->{VideoFragment::class.java
               Log.d("Mchawtchwia","hada makan")}
           R.id.nav_news ->NewsFragment ::class.java
           R.id.nav_veille->VeilleFragment::class.java
        }

        return super.onOptionsItemSelected(item)
    }
/*
    private fun loadVideos() {

       var user1 = User("nsad", "Ayoub")
        var ved = Video(user1,"My first video")
        videoList.add(ved)
        var user = User("nsad", " Mazrou")
        var ved1 = Video(user,"My first video")
        videoList.add(ved1)
         user = User("nsad", " Mazrou")
         ved1 = Video(user,"My first video")
        videoList.add(ved1)

        adapter.notifyDataSetChanged()
    }

   /* private fun creatFragementVideo(){
        val transaction = manager.beginTransaction()
        var fragment = VideoFragment()
        transaction.replace(R.id.home_fragment ,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun creatFragementVeille(){
        val transaction = manager.beginTransaction()
        var fragment = VeilleFragment()
        transaction.replace(R.id.home_fragment ,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun creatFragementNews(){
        val transaction = manager.beginTransaction()
        var fragment = VideoFragment()
        transaction.replace(R.id.home_fragment ,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    } */
}*/
*/