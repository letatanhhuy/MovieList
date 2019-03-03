package com.example.movielist.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.movielist.Fragment.MovieListFragment
import com.example.movielist.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame, MovieListFragment()).commit()
        setContentView(R.layout.activity_main)
    }
}
