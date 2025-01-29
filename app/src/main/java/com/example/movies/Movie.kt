package com.example.movies

import com.google.gson.annotations.SerializedName



data class Movie (
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String,

)

class MovieList {
    @SerializedName("Search")
    val movies: List<Movie>? = null // ...
}