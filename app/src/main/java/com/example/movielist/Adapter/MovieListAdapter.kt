package com.example.movielist.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movielist.Entity.Movie
import com.example.movielist.R
import kotlinx.android.synthetic.main.movie_row_view.view.*

class MovieListAdapter(var data:List<Movie>):RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    class MovieHolder(var rowView:View):RecyclerView.ViewHolder(rowView)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieHolder {
        var rowView = LayoutInflater.from(p0.context).inflate(R.layout.movie_row_view,p0, false)
        return MovieHolder(rowView)
    }

    override fun onBindViewHolder(p0: MovieHolder, p1: Int) {
        p0.rowView.txtTitle.text = data.get(p1).Title
    }

    override fun getItemCount(): Int {
        return data.size
    }
}