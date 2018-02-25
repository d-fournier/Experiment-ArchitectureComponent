package me.dfournier.architecturecomponent.movie.presentation.domain

import io.reactivex.Observable
import io.reactivex.Single
import me.dfournier.architecturecomponent.model.Movie
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by dfournier on 27/02/18.
 */
class MovieService
@Inject constructor() {

    private val list = listOf(
            Movie(1, "Lord of the Ring"),
            Movie(2, "Harry Potter"),
            Movie(3, "Matrix"),
            Movie(4, "Avatar")
    )

    private val myList = ArrayList<Movie>()

    fun getMovieList(): Observable<List<Movie>> {
        if (Random().nextInt(2) == 1) {
            return Single.timer(2500L, TimeUnit.MILLISECONDS)
                    .flatMapObservable { Observable.error<List<Movie>>(IllegalStateException()) }
        } else {
            return Observable.interval(2500L, TimeUnit.MILLISECONDS)
                    .take(4)
                    .map { list[it.toInt()] }
                    .doOnNext { myList.add(it) }
                    .map { myList }
        }
    }

    fun getMovie(id: Long): Single<Movie> {
        return Single.just(list[id.toInt() - 1])
    }

}