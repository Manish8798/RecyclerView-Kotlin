package com.example.terasoltask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.terasoltask.databinding.RowItemBinding

class MyAdapter(
    private val movies: List<Movie>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

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

    inner class MyViewHolder(private val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(movie: Movie) {
            binding.apply {
                movieName.text = movie.title
            }
        }

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val currentPos = adapterPosition
            listener.onItemClick(currentPos)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}