package com.example.terasoltask

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.terasoltask.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), MyAdapter.OnItemClickListener {
    private val TAG: String = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var movie: List<Movie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val request = Api.create()
        val call = request.getMovies()

        call.enqueue(object : Callback<List<Movie>> {

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    binding.recyclerView.apply {
                        movie = response.body()!!
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = MyAdapter(movie, this@MainActivity)
                        Log.d(TAG, "onResponse: ${response.body()}")
                    }
                }
            }
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
            }
        })
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onItemClick: $position")
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie_img", movie[position].info.image_url)
        intent.putExtra("movie_rating", movie[position].info.rating)
        intent.putExtra("movie_rank", movie[position].info.rank.toString())
        intent.putExtra("movie_title", movie[position].title)
        intent.putExtra("movie_year", movie[position].year.toString())
        startActivity(intent)
    }
}