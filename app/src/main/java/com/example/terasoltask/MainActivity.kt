package com.example.terasoltask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.terasoltask.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    private lateinit var binding: ActivityMainBinding
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
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = MyAdapter(response.body()!!)
                        Log.d(TAG, "onResponse: ${response.body()}")
                    }
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
            }
        })
    }
}