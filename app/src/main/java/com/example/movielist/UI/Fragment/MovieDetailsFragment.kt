package com.example.movielist.UI.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movielist.Entity.Movie
import com.example.movielist.R
import kotlinx.android.synthetic.main.movie_detail_view.*

class MovieDetailsFragment:Fragment() {
    private lateinit var curMovie:Movie
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_detail_view, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var b = this.arguments
        curMovie = b?.getSerializable("CurMovie") as Movie
        txtTitle.text = curMovie.title
        txtDetail.text = curMovie.overview
    }

}