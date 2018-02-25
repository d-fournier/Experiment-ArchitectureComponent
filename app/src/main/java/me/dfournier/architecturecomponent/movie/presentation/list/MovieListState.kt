package me.dfournier.architecturecomponent.movie.presentation.list

import me.dfournier.architecturecomponent.model.Movie

/**
 * Created by dfournier on 16/02/18.
 */
data class MovieListState(
        val movieList: List<Movie>,
        val loading: Boolean
)