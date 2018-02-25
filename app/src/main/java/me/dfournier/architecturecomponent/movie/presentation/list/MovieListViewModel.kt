package me.dfournier.architecturecomponent.movie.presentation.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import me.dfournier.architecturecomponent.*
import me.dfournier.architecturecomponent.movie.presentation.domain.MovieService
import javax.inject.Inject

/**
 * Created by dfournier on 16/02/18.
 */
class MovieListViewModel
@Inject constructor(
        private val movieService: MovieService
) : ViewModel() {

    val state = MutableLiveData<MovieListState>()
    val event = SingleLiveEvent<Event>()

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
                            internalState = internalState.copy(movieList = it)
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

    fun onMovieSelected(id: Long) {
        event.postValue(NavigationEvent(
                NAV_DETAIL_VIEW, id
        ))
    }

    override fun onCleared() {
        disposableList.dispose()
    }

}