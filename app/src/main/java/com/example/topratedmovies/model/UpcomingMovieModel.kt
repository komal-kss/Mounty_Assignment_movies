package com.example.topratedmovies.model

class UpcomingMovieModel {
    var imageUrl: String? = null
    var title: String? = null
    var description: String? = null
    var rating: String? = null
    var releaseDate: String? = null

    constructor() {}
    constructor(imageUrl: String?, title: String?, description: String?, rating: String?, releaseDate: String?) {
        this.imageUrl = imageUrl
        this.title = title
        this.description = description
        this.rating = rating
        this.releaseDate = releaseDate
    }

}