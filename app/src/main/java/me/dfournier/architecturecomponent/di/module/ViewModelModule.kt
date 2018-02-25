package me.dfournier.architecturecomponent.di.module

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.dfournier.architecturecomponent.di.annotation.ViewModelKey
import me.dfournier.architecturecomponent.movie.presentation.detail.MovieDetailViewModel
import me.dfournier.architecturecomponent.movie.presentation.list.MovieListViewModel

/**
 * Created by dfournier on 01/03/18.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindListViewModel(viewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

}