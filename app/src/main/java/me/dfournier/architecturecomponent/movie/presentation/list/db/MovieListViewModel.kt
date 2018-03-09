package me.dfournier.architecturecomponent.movie.presentation.list.db

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import me.dfournier.architecturecomponent.*
import me.dfournier.architecturecomponent.model.Clickable
import me.dfournier.architecturecomponent.movie.domain.MovieService
import me.dfournier.architecturecomponent.movie.presentation.common.MovieItemDB
import javax.inject.Inject

/**
 * Created by dfournier on 16/02/18.
 */
class MovieListViewModel
@Inject constructor(
        private val movieService: MovieService
) : ViewModel() {

    val event = SingleLiveEvent<Event>()
    val state = MovieListStateDB(this::refreshMovieList)

    private val disposableList = CompositeDisposable()

    init {
        refreshMovieList()
    }

    fun refreshMovieList() {
        state.loading.set(true)
        val disposable = movieService.getMovieList()
                .subscribe(
                        {
                            val list = it.map {
                                MovieItemDB(it.id, it.title, this::onMovieSelected)
                            }
                            state.movieList.postValue(list)
                        },
                        {
                            event.postValue(ErrorEvent("Loading Error"))
                            state.loading.set(false)
                        },
                        {
                            state.loading.set(false)
                        }
                )
        disposableList.add(disposable)
    }

    private fun onMovieSelected(id: Long) {
        event.postValue(NavigationEvent(
                NAV_DETAIL_VIEW, id
        ))
    }

    override fun onCleared() {
        disposableList.dispose()
    }

}