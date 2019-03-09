package com.example.movielist.Adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movielist.Entity.Movie
import kotlinx.android.synthetic.main.movie_row_view.view.*
import com.example.movielist.Activity.MainActivity
import com.example.movielist.Fragment.MovieDetailsFragment
import com.example.movielist.R


class MovieListAdapter(var data:List<Movie>):RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    lateinit var mContext:Context

    class MovieHolder(var rowView:View):RecyclerView.ViewHolder(rowView)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieHolder {
        var rowView = LayoutInflater.from(p0.context).inflate(R.layout.movie_row_view,p0, false)
        mContext = p0.context
        return MovieHolder(rowView)
    }

    override fun onBindViewHolder(p0: MovieHolder, p1: Int) {
        p0.rowView.txtTitle.text = data.get(p1).title
        p0.rowView.setOnClickListener {
            Log.d(TAG,"setOnClickListener new :{$p1}")
            var bundle = Bundle()
            bundle.putSerializable("CurMovie", data[p1])
            bundle.putString("CurMovieTitle", data[p1].title)
            var movieDetailsFragment = MovieDetailsFragment()
            movieDetailsFragment.arguments = bundle
            switchContent(R.id.mainFrame, movieDetailsFragment)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun switchContent(id: Int, fragment: Fragment) {
        if (mContext is MainActivity) {
            Log.d(TAG,"switchContent")
            val mainActivity = mContext as MainActivity
            mainActivity.switchContent(id, fragment)
        }

    }

    companion object {
        private val TAG = "Pika-MovieListAdapter"
    }
}