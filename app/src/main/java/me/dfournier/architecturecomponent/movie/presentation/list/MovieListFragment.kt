package me.dfournier.architecturecomponent.movie.presentation.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import dagger.android.support.AndroidSupportInjection
import me.dfournier.architecturecomponent.*
import me.dfournier.architecturecomponent.base.BaseFragment
import me.dfournier.architecturecomponent.movie.list.MovieListAdapter
import me.dfournier.architecturecomponent.movie.presentation.detail.MovieDetailFragment
import me.dfournier.architecturecomponent.util.observeSafe
import javax.inject.Inject

/**
 * Created by dfournier on 16/02/18.
 */
class MovieListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    lateinit var viewModel: MovieListViewModel

    lateinit var refresh: SwipeRefreshLayout
    lateinit var list: RecyclerView
    lateinit var adapter: MovieListAdapter

    override fun getLayoutId(): Int = R.layout.fragment_list

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun bindView(view: View) {
        super.bindView(view)
        refresh = view.findViewById(R.id.refresh)
        refresh.setOnRefreshListener {
            viewModel.refreshMovieList()
        }
        adapter = MovieListAdapter {
            viewModel.onMovieSelected(it)
        }
        list = view.findViewById(R.id.list_item)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
        viewModel.state.observeSafe(this) {
            refresh.isRefreshing = it.loading
            adapter.list = it.movieList
        }

        viewModel.event.observe(this, Observer {
            when (it) {
                is NavigationEvent -> navigateTo(it)
                is ErrorEvent -> Snackbar.make(getView()!!, it.errorMessage, Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    fun navigateTo(event: NavigationEvent) {
        val fragment = when (event.tag) {
            NAV_DETAIL_VIEW -> MovieDetailFragment.newInstance(event.info!!)
            else -> null
        }

        if (fragment != null) {
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit()
        }
    }

    companion object {
        fun newInstance(): MovieListFragment {
            return MovieListFragment()
        }
    }

}

