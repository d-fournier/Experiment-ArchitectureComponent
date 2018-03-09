package me.dfournier.architecturecomponent.movie.presentation.list.refresh

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import me.dfournier.architecturecomponent.*
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

    val state = MutableLiveData<MovieListState>()
    private var internalState = MovieListState(emptyList(), false)


    private val disposableList = CompositeDisposable()

    init {
        refreshMovieList()
    }

    fun refreshMovieList() {
        internalState = internalState.copy(loading = true)
        state.postValue(internalState)

        val disposable = movieService.getMovieList()
                .subscribe(
                        {
                            val list = it.map {
                                MovieItemDB(it.id, it.title, this::onMovieSelected)
                            }

                            internalState = internalState.copy(
                                    movieList = list
                            )
                            state.postValue(internalState)
                        },
                        {
                            event.postValue(ErrorEvent("Loading Error"))
                            internalState = internalState.copy(loading = false)
                            state.postValue(internalState)
                        },
                        {
                            internalState = internalState.copy(loading = false)
                            state.postValue(internalState)
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