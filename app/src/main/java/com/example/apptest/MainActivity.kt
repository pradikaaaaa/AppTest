package com.example.apptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import com.example.apptest.fragment.FavoritFragment
import com.example.apptest.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var homeFragment : HomeFragment
    lateinit var favoritFragment: FavoritFragment

    lateinit var fragTrans : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.background = null
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        homeFragment = HomeFragment()
        favoritFragment = FavoritFragment()

        fragTrans = supportFragmentManager.beginTransaction()
        fragTrans.replace(R.id.frameHome,homeFragment).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home->{
                fragTrans = supportFragmentManager.beginTransaction()
                fragTrans.replace(R.id.frameHome,homeFragment).commit()
            }
            R.id.nav_bookmark->{
                fragTrans = supportFragmentManager.beginTransaction()
                fragTrans.replace(R.id.frameHome,favoritFragment).commit()
            }
        }
        return true
    }
}