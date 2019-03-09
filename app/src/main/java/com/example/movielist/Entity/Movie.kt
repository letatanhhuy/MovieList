package com.example.movielist.Entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Movie(@SerializedName("original_title")var title:String,
            @SerializedName("overview")var overview:String):Serializable