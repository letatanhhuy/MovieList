package com.example.movielist.Network
import android.util.Log
import com.example.movielist.Entity.Movie
import com.example.movielist.Entity.NetworkRespond
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class NetworkProvider {
    companion object {
        private var TAG = "Pikachu::" + this.javaClass.simpleName
        lateinit var networkUpdateListener: NetworkMovieUpdateListener
        private const val URL = "http://api.themoviedb.org/3/"
        private const val key = "047e9d268a9694e6ee43d7ced185b917"
        private var retrofit = Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
        private var networkService = retrofit.create(NetworkService::class.java)
        fun getTopRatedMovies(pageIndex:Int, api_key:String = key):NetworkRespond {
            var getTopRatedCall = networkService.getTopRatedMovie(api_key, pageIndex)
            getTopRatedCall.enqueue(
                object : Callback<NetworkRespond> {
                    override fun onResponse(call: Call<NetworkRespond>, response: Response<NetworkRespond>) {
                        Log.d(TAG, "onResponse Test:{${response.body()?.result?.size}}")
                        var movieList = response.body()?.result as ArrayList<Movie>
                        networkUpdateListener.getMoreTopRatedMoviesSuccess(movieList)
                    }
                    override fun onFailure(call: Call<NetworkRespond>, t: Throwable) {
                        Log.d(TAG, "onFailure est:{${t.message}")
                        networkUpdateListener.getTopRatedMoviesFailed()
                    }
                }
            )

            return NetworkRespond(ArrayList())
        }
    }

    interface NetworkMovieUpdateListener {
        fun getTopRatedMoviesSuccess(data:ArrayList<Movie>)
        fun getMoreTopRatedMoviesSuccess(data:ArrayList<Movie>)
        fun getTopRatedMoviesFailed()
    }
}