package com.minasmina.daggerplayground.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor() {

    init {
        println("MoviesRepository()")
    }

    fun getMovieById(id: Int): String = TITLES[id % TITLES.size]

    companion object {
        private val TITLES = arrayOf(
            "Lord of the Rings",
            "Scarface",
            "The Godfather",
            "Spiderman",
            "Superman",
            "Batman"
        )
    }
}