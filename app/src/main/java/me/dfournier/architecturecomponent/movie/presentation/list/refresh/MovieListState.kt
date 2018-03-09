package me.dfournier.architecturecomponent.movie.presentation.list.refresh

import me.dfournier.architecturecomponent.movie.presentation.common.MovieItemDB

/**
 * Created by dfournier on 16/02/18.
 */
data class MovieListState(
        val movieList: List<MovieItemDB>,
        val loading: Boolean
)