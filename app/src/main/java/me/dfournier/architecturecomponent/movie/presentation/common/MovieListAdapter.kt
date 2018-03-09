package me.dfournier.architecturecomponent.movie.list

import me.dfournier.architecturecomponent.R
import me.dfournier.architecturecomponent.base.presentation.adapter.ViewBindingRecyclerViewAdapter
import me.dfournier.architecturecomponent.movie.presentation.common.MovieItemDB

/**
 * @author dfournier
 */
class MovieListAdapter : ViewBindingRecyclerViewAdapter<MovieItemDB>() {

    override fun getLayoutIdForPosition(position: Int): Int = R.layout.item_movie

}
