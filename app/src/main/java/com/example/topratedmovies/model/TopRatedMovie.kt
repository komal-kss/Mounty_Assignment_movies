package com.example.topratedmovies.model

class TopRatedMovie {
    var imageUrl: String? = null
    var title: String? = null
    var description: String? = null
    var ratings: String? = null
    var releaseDate: String? = null

    constructor() {}

    constructor(imageUrl: String?, title: String?, description: String?, releaseDate: String?, ratings: String?) {
        this.imageUrl = imageUrl
        this.title = title
        this.description = description
        this.releaseDate = releaseDate
        this.ratings = ratings
    }

}