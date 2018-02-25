package me.dfournier.architecturecomponent.movie.presentation.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import me.dfournier.architecturecomponent.ErrorEvent
import me.dfournier.architecturecomponent.Event
import me.dfournier.architecturecomponent.SingleLiveEvent
import me.dfournier.architecturecomponent.movie.presentation.domain.MovieService
import javax.inject.Inject

/**
 * Created by dfournier on 18/02/18.
 */
class MovieDetailViewModel
@Inject constructor(
        private val movieService: MovieService
) : ViewModel() {

    val state = MutableLiveData<MovieDetailState>()
    val event = SingleLiveEvent<Event>()

    private var currentState = MovieDetailState(null)
    private val disposableList = CompositeDisposable()

    fun start(id: Long) {
        getMovieDetail(id)
    }

    private fun getMovieDetail(id: Long) {
        disposableList.add(
                movieService.getMovie(id)
                        .subscribe(
                                {
                                    currentState = currentState.copy(movie = it)
                                    state.postValue(currentState)
                                },
                                {
                                    currentState = currentState.copy(movie = null)
                                    state.postValue(currentState)
                                    event.postValue(ErrorEvent("Error while fetching details"))
                                }
                        )
        )
    }

}