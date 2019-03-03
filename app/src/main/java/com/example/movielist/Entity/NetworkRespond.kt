package com.example.movielist.Entity

import com.google.gson.annotations.SerializedName

class NetworkRespond(@SerializedName("results") var result:ArrayList<Movie>)