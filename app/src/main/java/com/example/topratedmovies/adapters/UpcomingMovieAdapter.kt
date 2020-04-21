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
import com.example.topratedmovies.adapters.UpcomingMovieAdapter.UpcomingMovieHolder
import com.example.topratedmovies.model.UpcomingMovieModel

class UpcomingMovieAdapter(private val context: Context, private val upcomingMovieModelList: List<UpcomingMovieModel>) : RecyclerView.Adapter<UpcomingMovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_upcoming_movie, parent, false)
        return UpcomingMovieHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomingMovieHolder, position: Int) {
        holder.title.text = upcomingMovieModelList[position].title
        //call the method to set the image
        holder.onImagePoster(upcomingMovieModelList[position].imageUrl)
        val title = upcomingMovieModelList[position].title
        val desc = upcomingMovieModelList[position].description
        val poster = upcomingMovieModelList[position].imageUrl
        val rating = upcomingMovieModelList[position].rating
        val release_date = upcomingMovieModelList[position].releaseDate
        holder.cardView.setOnClickListener {
            val movieIntent = Intent(context, UpcomingMovieDetails::class.java)
            movieIntent.putExtra("title", title)
            movieIntent.putExtra("desc", desc)
            movieIntent.putExtra("poster", poster)
            movieIntent.putExtra("rating", rating)
            movieIntent.putExtra("release_date", release_date)
            context.startActivity(movieIntent)
        }
        Log.d("inside onBIndHolder", upcomingMovieModelList[position].imageUrl)
    }

    override fun getItemCount(): Int {
        return upcomingMovieModelList.size
    }

    inner class UpcomingMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var rating: TextView
        var movieImage: ImageView
        var cardView: CardView
        fun onImagePoster(imagePath: String?) { //placeholder image
            Glide.with(context).load("https://image.tmdb.org/t/p/w500$imagePath").into(movieImage)
        }

        init {
            title = itemView.findViewById(R.id.upcoming_titleId)
            rating = itemView.findViewById(R.id.upcoming_ratingId)
            movieImage = itemView.findViewById(R.id.upcoming_imageId)
            cardView = itemView.findViewById(R.id.cardView)
        }
    }

}