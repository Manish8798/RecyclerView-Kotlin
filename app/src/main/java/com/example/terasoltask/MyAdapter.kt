package com.example.terasoltask

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.terasoltask.databinding.RowItemBinding

class MyAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MyViewHolder(private val binding: RowItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.apply {
            movieName.text = movie.title
            movieName.setOnClickListener {
                val intent = Intent(binding.root.context, MovieDetailActivity::class.java)
                intent.putExtra("movie_img", movie.info.image_url)
                intent.putExtra("movie_rating", movie.info.rating)
                intent.putExtra("movie_rank", movie.info.rank.toString())
                intent.putExtra("movie_title", movie.title)
                intent.putExtra("movie_year", movie.year.toString())
                binding.root.context.startActivity(intent)

            }
        }
    }
}

class MovieComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}