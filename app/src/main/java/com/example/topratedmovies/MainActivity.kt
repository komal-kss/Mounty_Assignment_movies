package com.example.topratedmovies

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topratedmovies.adapters.TopRatedMovieAdapter
import com.example.topratedmovies.adapters.UpcomingMovieAdapter
import com.example.topratedmovies.model.TopRatedMovie
import com.example.topratedmovies.model.UpcomingMovieModel
import com.example.topratedmovies.pojo.TopRatedMoviesResponse
import com.example.topratedmovies.pojo.UpcomingMoviesResponse
import com.example.topratedmovies.retrofit.TmdbApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

open class MainActivity : AppCompatActivity() {
    var progressDialog1: ProgressDialog? = null
    var progressDialog2: ProgressDialog? = null
//    var retrofit: Retrofit? = null
    var topRatedMovieAdapter: TopRatedMovieAdapter? = null
    var upcomingMovieAdapter: UpcomingMovieAdapter? = null
    var recyclerViewtop: RecyclerView? = null
    var recylerViewUp: RecyclerView? = null
    var topRatedMovieList: MutableList<TopRatedMovie>? = null
    var upcomingMovieModelList: MutableList<UpcomingMovieModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewtop = findViewById(R.id.recycler_viewtop) as RecyclerView
        recylerViewUp = findViewById(R.id.recycler_viewupcoming) as RecyclerView
        topRatedMovieList = ArrayList()
        upcomingMovieModelList = ArrayList()
        topRatedMovieAdapter = TopRatedMovieAdapter(this@MainActivity, topRatedMovieList as ArrayList<TopRatedMovie>)
        recyclerViewtop!!.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
        recyclerViewtop!!.setAdapter(topRatedMovieAdapter)
        fetchTopRatedMoviesData()
        upcomingMovieAdapter = UpcomingMovieAdapter(this@MainActivity, upcomingMovieModelList as ArrayList<UpcomingMovieModel>)
        recylerViewUp!!.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
        recylerViewUp!!.setAdapter(upcomingMovieAdapter)
        fetchUpcomingMoviesData()
    }

    fun fetchUpcomingMoviesData() {

       var retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val tmdbApi = retrofit.create(TmdbApi::class.java)
        val upcomingMoviesResponseCall = tmdbApi.getUpcomingMovies(Constants.API_KEY)
        upcomingMoviesResponseCall.enqueue(object : Callback<UpcomingMoviesResponse?> {
            override fun onResponse(call: Call<UpcomingMoviesResponse?>, response: Response<UpcomingMoviesResponse?>) {
                if (response != null) { //
                    Log.d("tagg", "raeagtg46h3")
                    val upcomingMoviesResponse = response.body()
                    val resultsBeanList = upcomingMoviesResponse!!.results
                    Log.d("Check", resultsBeanList.toString())
                    if (resultsBeanList != null) {
                        for (resultsBean in resultsBeanList) {
                            val title = resultsBean.title
                            val imageUrl = resultsBean.posterPath
                            val description = resultsBean.overview
                            val releaseDate = resultsBean.releaseDate
                            val ratings = resultsBean.popularity.toString()
                            Log.d("values", "$title $imageUrl $releaseDate $ratings")
                            val upcomingMovieModel = UpcomingMovieModel(imageUrl, title, description, releaseDate, ratings)
                            upcomingMovieModelList!!.add(upcomingMovieModel)
                        }
                    }
                    upcomingMovieAdapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<UpcomingMoviesResponse?>, t: Throwable) {
                Log.i("tagg", "raeagtg46h3 failed")
            }
        })
    }

    fun fetchTopRatedMoviesData() {
        //for progressbarDialog
        progressDialog2 = ProgressDialog(this@MainActivity, R.style.AppTheme_Dark_Dialog)
        progressDialog2!!.isIndeterminate = true
        progressDialog2!!.setMessage("Fetching Movies ...")
        progressDialog2!!.setCanceledOnTouchOutside(false)
        progressDialog2!!.show()
       var  retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val tmdbApi = retrofit.create(TmdbApi::class.java)
        val topRatedMoviesResponseCall = tmdbApi.getTopRatedMovies(Constants.API_KEY)
        topRatedMoviesResponseCall.enqueue(object : Callback<TopRatedMoviesResponse?> {
            override fun onResponse(call: Call<TopRatedMoviesResponse?>, response: Response<TopRatedMoviesResponse?>) {
                if (response.body() != null) {
                    progressDialog2!!.dismiss()
                    Log.d("Tag-", response.body().toString())
                    val topRatedMoviesResponse = response.body()
                    val resultsBeanList = topRatedMoviesResponse!!.results
                    Log.d("Check", resultsBeanList.toString())
                    if (resultsBeanList != null) {
                        for (resultsBean in resultsBeanList) {
                            val title = resultsBean.title
                            val imageUrl = resultsBean.posterPath
                            val description = resultsBean.overview
                            val releaseDate = resultsBean.releaseDate
                            val ratings = resultsBean.popularity.toString()
                            Log.d("values", "$title $imageUrl")
                            val topRatedMovie = TopRatedMovie(imageUrl, title, description, releaseDate, ratings)
                            topRatedMovieList!!.add(topRatedMovie)
                        }
                    }
                    topRatedMovieAdapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<TopRatedMoviesResponse?>, t: Throwable) {}
        })
    }
}