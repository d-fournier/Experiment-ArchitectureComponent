package me.dfournier.architecturecomponent.movie

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.dfournier.architecturecomponent.di.annotation.FragmentScoped
import me.dfournier.architecturecomponent.movie.presentation.detail.MovieDetailFragment

/**
 * Created by dfournier on 01/03/18.
 */
@Module
abstract class MovieModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun listFragmentRefresh(): me.dfournier.architecturecomponent.movie.presentation.list.refresh.MovieListFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun listFragmentDb(): me.dfournier.architecturecomponent.movie.presentation.list.db.MovieListFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun detailFragment(): MovieDetailFragment

}