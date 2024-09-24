package com.artonov.watchnext

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artonov.watchnext.databinding.ItemRowMovieBinding

class ListMovieAdapter(private val listMovie: ArrayList<Movies>) :
    RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    class ListViewHolder(
        val binding: ItemRowMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ListMovieAdapter.ListViewHolder, position: Int) {
        val (title, plot, runtime, rating, cover) = listMovie[position]
        holder.binding.tvItemName.text = title
        holder.binding.tvItemDescription.text = plot
        holder.binding.tvItemRuntime.text = runtime
        holder.binding.tvItemRating.text = rating
        holder.binding.imgItemPhoto.setImageResource(cover)

        holder.itemView.setOnClickListener() {
            val intentDetail = Intent(holder.itemView.context, MovieDetails::class.java)
            intentDetail.putExtra(MovieDetails.EXTRA_MOVIE, listMovie[holder.position])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}