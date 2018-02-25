package me.dfournier.architecturecomponent.movie

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.dfournier.architecturecomponent.di.annotation.FragmentScoped
import me.dfournier.architecturecomponent.movie.presentation.detail.MovieDetailFragment
import me.dfournier.architecturecomponent.movie.presentation.list.MovieListFragment

/**
 * Created by dfournier on 01/03/18.
 */
@Module
abstract class MovieModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun listFragment(): MovieListFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun detailFragment(): MovieDetailFragment

}