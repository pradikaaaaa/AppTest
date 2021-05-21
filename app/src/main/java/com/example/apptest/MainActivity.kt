package com.example.apptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import com.example.apptest.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var homeFragment : HomeFragment

    lateinit var fragTrans : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(1).isEnabled = false
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        homeFragment = HomeFragment()

        fragTrans = supportFragmentManager.beginTransaction()
        fragTrans.replace(R.id.frameHome,homeFragment).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home->{
                fragTrans = supportFragmentManager.beginTransaction()
                fragTrans.replace(R.id.frameHome,homeFragment).commit()
            }
        }
        return true
    }
}