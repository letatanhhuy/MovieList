package com.example.movielist.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.movielist.Fragment.MovieListFragment
import com.example.movielist.R
import android.support.v4.app.Fragment
import android.util.Log
import com.example.movielist.Adapter.MovieListAdapter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame, MovieListFragment()).commit()
        setContentView(R.layout.activity_main)
    }

    fun switchContent(id: Int, fragment: Fragment) {
        Log.d("Pika","switchContent main activity")
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(id, fragment, fragment.toString())
        ft.addToBackStack(null)
        ft.commit()
    }
}
