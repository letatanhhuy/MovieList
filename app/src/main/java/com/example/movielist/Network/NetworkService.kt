package com.example.movielist.Network

import com.example.movielist.Entity.NetworkRespond
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("movie/top_rated")
    fun getTopRatedMovie(@Query("api_key") key:String
                         , @Query("page") pageIndex:Int):Call<NetworkRespond>
}