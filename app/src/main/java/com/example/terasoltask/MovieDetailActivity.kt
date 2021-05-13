package com.example.terasoltask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.terasoltask.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent != null) {
            binding.apply {
                movieTitle.text = intent.getStringExtra("movie_title")
                movieRank.text ="Movie Rank: " + intent.getStringExtra("movie_rank")
                movieYear.text ="Movie Year: " + intent.getStringExtra("movie_year")
            }
        }
    }
}