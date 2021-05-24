package com.example.movieskritou

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movieskritou.fragments.FavoritesFragment
import com.example.movieskritou.fragments.HomeFragment
import com.example.movieskritou.fragments.SearchFragment
import com.example.movieskritou.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()
        //MovieDatabase.get(application)

        if(databaseFileExists()){
            Log.d("db", "yes")
        }
        else
        {
            Log.d("db1", "no")

        }
    }

    private fun setUpTabs() {

        val adapter = ViewPagerAdapter(supportFragmentManager)

        // Titles of tabs
        adapter.addFragment(HomeFragment(), "Popular")
        adapter.addFragment(FavoritesFragment(), "Playing")
        adapter.addFragment(SearchFragment(), "Search")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        // Icons for the tabs
        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_home_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_favorite_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_search_24)
    }

    private fun databaseFileExists(): Boolean {
        return try {
            File(getDatabasePath("MovieDatabase").absolutePath).exists()

        }catch (e: Exception){
            false
        }
    }
}