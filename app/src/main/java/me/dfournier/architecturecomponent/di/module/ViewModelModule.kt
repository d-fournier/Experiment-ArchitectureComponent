package me.dfournier.architecturecomponent.di.module

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.dfournier.architecturecomponent.di.annotation.ViewModelKey
import me.dfournier.architecturecomponent.movie.presentation.detail.MovieDetailViewModel

/**
 * @author dfournier
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(me.dfournier.architecturecomponent.movie.presentation.list.db.MovieListViewModel::class)
    abstract fun bindListViewModelDb(viewModel: me.dfournier.architecturecomponent.movie.presentation.list.db.MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(me.dfournier.architecturecomponent.movie.presentation.list.refresh.MovieListViewModel::class)
    abstract fun bindListViewModelRefresh(viewModel: me.dfournier.architecturecomponent.movie.presentation.list.refresh.MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

}