package com.example.topratedmovies.pojo

import com.google.gson.annotations.SerializedName

//import javax.annotation.Generated;
//@Generated("net.hexar.json2pojo")
class TopRatedMoviesResponse {
    @SerializedName("page")
    var page: Long? = null
    @SerializedName("results")
    var results: List<TopRatedResult>? = null
    @SerializedName("total_pages")
    var totalPages: Long? = null
    @SerializedName("total_results")
    var totalResults: Long? = null

}