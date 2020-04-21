package com.example.topratedmovies.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.topratedmovies.R
import com.example.topratedmovies.UpcomingMovieDetails
import com.example.topratedmovies.adapters.TopRatedMovieAdapter.TopRatedMovieHolder
import com.example.topratedmovies.model.TopRatedMovie

class TopRatedMovieAdapter(private val context: Context, private val topRatedMovieList: List<TopRatedMovie>) : RecyclerView.Adapter<TopRatedMovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMovieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_top_rated_movie, parent, false)
        return TopRatedMovieHolder(view)
    }

    override fun onBindViewHolder(holder: TopRatedMovieHolder, position: Int) {
        holder.title.text = topRatedMovieList[position].title
        //call the method to set the image
        holder.onImagePoster(topRatedMovieList[position].imageUrl)
        val title = topRatedMovieList[position].title
        val desc = topRatedMovieList[position].description
        val poster = topRatedMovieList[position].imageUrl
        val rating = topRatedMovieList[position].ratings
        val release_date = topRatedMovieList[position].releaseDate
        holder.cardView.setOnClickListener {
            val movieIntent = Intent(context, UpcomingMovieDetails::class.java)
            movieIntent.putExtra("title", title)
            movieIntent.putExtra("desc", desc)
            movieIntent.putExtra("poster", poster)
            movieIntent.putExtra("rating", rating)
            movieIntent.putExtra("release_date", release_date)
            context.startActivity(movieIntent)
        }
        Log.d("inside onBIndHolder", topRatedMovieList[position].imageUrl)
    }

    override fun getItemCount(): Int {
        return topRatedMovieList.size
    }

    inner class TopRatedMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var rating: TextView
        var movieImage: ImageView
        var cardView: CardView
        fun onImagePoster(imagePath: String?) { //placeholder image
            Glide.with(context).load("https://image.tmdb.org/t/p/w500$imagePath").into(movieImage)
        }

        init {
            title = itemView.findViewById(R.id.top_rated_titleId)
            rating = itemView.findViewById(R.id.top_rated_ratingId)
            movieImage = itemView.findViewById(R.id.top_rated_imageId)
            cardView = itemView.findViewById(R.id.top_rated_movie_cardId)
        }
    }

}