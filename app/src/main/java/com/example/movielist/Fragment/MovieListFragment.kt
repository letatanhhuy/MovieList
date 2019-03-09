package com.example.movielist.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.movielist.Adapter.MovieListAdapter
import com.example.movielist.Entity.Movie
import com.example.movielist.Network.NetworkProvider
import com.example.movielist.R
import kotlinx.android.synthetic.main.movie_list_view.view.*

class MovieListFragment:Fragment() {
    lateinit var movieListRecyclerView:RecyclerView
    lateinit var movieListAdapter: MovieListAdapter
    lateinit var movieListLayoutmanager: LinearLayoutManager
    lateinit var movieList:ArrayList<Movie>
    var currentPage:Int = 1
    var isEndPage = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.movie_list_view, container, false)
        movieList = ArrayList()
        movieListLayoutmanager = LinearLayoutManager(context)
        movieListAdapter = MovieListAdapter(movieList)
        movieListRecyclerView = view.movieRecycleView.apply {
            adapter = movieListAdapter
            layoutManager = movieListLayoutmanager
        }
        currentPage = 1
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networkUpdateListener:NetworkProvider.NetworkMovieUpdateListener = object : NetworkProvider.NetworkMovieUpdateListener {
            override fun getTopRatedMoviesFailed() {
                Toast.makeText(context, " UPDATE FAILED", Toast.LENGTH_LONG)
                Log.d(TAG, "getTopRatedMoviesFailed")
            }

            override fun getTopRatedMoviesSuccess(data: ArrayList<Movie>) {
                Log.d(TAG, "getTopRatedMoviesSuccess Test:{${data.size}}")
                currentPage = 1
                updateMovieList(true, data)
            }

            override fun getMoreTopRatedMoviesSuccess(data: ArrayList<Movie>) {
                Log.d(TAG, "getMoreTopRatedMoviesSuccess Test:{${data.size}}")
                updateMovieList(false, data)
                currentPage++
                isEndPage = false
            }
        }
        NetworkProvider.networkUpdateListener = networkUpdateListener
        NetworkProvider.getTopRatedMovies(currentPage)
        movieListRecyclerView.addOnScrollListener (
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        Log.d(TAG, "onScrolled")
                        var visibleItemCount = movieListLayoutmanager.childCount
                        var totalItemCount = movieListLayoutmanager.itemCount
                        var currentFirstVisibleIteam = movieListLayoutmanager.findFirstVisibleItemPosition()
                        if (totalItemCount == visibleItemCount + currentFirstVisibleIteam) {
                            Log.d(TAG, "onScrolled - Last Item")
                            if(!isEndPage) {
                                Log.d(TAG, "onScrolled - Last Item load more data")
                                NetworkProvider.getTopRatedMovies(currentPage)
                                isEndPage = true
                            }
                        }
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            }
        )
    }

    fun updateMovieList(isLoadMore:Boolean, data:ArrayList<Movie>) {
        if(isLoadMore) {
            movieList.clear()
        }
        for(movie in data) {
            movieList.add(movie)
        }
        movieListAdapter.notifyDataSetChanged()
    }

    companion object {
        private val TAG = "Pikachu::" + this.javaClass.simpleName
    }
}