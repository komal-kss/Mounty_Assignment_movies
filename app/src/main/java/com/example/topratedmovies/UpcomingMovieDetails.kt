package com.example.topratedmovies

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide

class UpcomingMovieDetails : MainActivity() {
    private var moviePoster: ImageView? = null
    private var movieDesc: TextView? = null
    private var movieReleaseDate: TextView? = null
    private val movieLength: TextView? = null
    private var titleView: TextView? = null
    private var movieRating: RatingBar? = null
    var id = 0
    var toolbar: Toolbar? = null
    var title: String? = null
    var poster: String? = null
    var desc: String? = null
    var release_date: String? = null
    var rating = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //for getting the full screen
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.upcoming_movie_details)
        val movieIntent = intent
        title = movieIntent.extras!!.getString("title")
        poster = movieIntent.extras!!.getString("poster")
        desc = movieIntent.extras!!.getString("desc")
        release_date = movieIntent.extras!!.getString("release_date")
        Log.d("title", "$title $poster $desc $release_date")
        moviePoster = findViewById(R.id.imageId) as ImageView
        movieDesc = findViewById(R.id.descId) as TextView
        movieReleaseDate = findViewById(R.id.release_dateId) as TextView
//        movieRating = findViewById(R.id.ratingId) as
        titleView = findViewById(R.id.title) as TextView
        titleView!!.setText(title)
        movieDesc!!.setText(desc)
        movieReleaseDate!!.setText("Release Date: $release_date")
        //Setting the values
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/$poster").into(moviePoster!!)
        //        movieDesc.setText(desc);
//        movieReleaseDate.setText(String.format("%s%s\ns%s", RELEASE_DATE, release_date, RATINGS,rating));
    }

    companion object {
        const val RELEASE_DATE = "Release Date:"
        const val RATINGS = "Ratings:"
        const val HOURS = "hrs"
    }
}