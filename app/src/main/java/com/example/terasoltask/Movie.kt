package com.example.terasoltask

data class Movie(
    val year: Int,
    val title: String,
    val info: Info
)
data class Info(
    val directors: List<String>,
    val release_date: String,
    val rating: String,
    val genres: List<String>,
    val image_url: String,
    val plot: String,
    val rank: Int,
    val running_time_secs: Int,
    val actors: List<String>
)
