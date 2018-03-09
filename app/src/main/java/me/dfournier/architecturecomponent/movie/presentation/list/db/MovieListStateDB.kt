package me.dfournier.architecturecomponent.movie.presentation.list.db

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import me.dfournier.architecturecomponent.movie.presentation.common.MovieItemDB

/**
 * @author dfournier
 */
class MovieListStateDB(val refresh: (() -> Unit)?) {

    val loading = ObservableBoolean()

    val movieList = MutableLiveData<List<MovieItemDB>>()

}