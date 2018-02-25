package me.dfournier.architecturecomponent.movie.presentation.detail

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.View
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_detail.*
import me.dfournier.architecturecomponent.CustomViewModelFactory
import me.dfournier.architecturecomponent.R
import me.dfournier.architecturecomponent.base.BaseFragment
import me.dfournier.architecturecomponent.util.observeSafe
import javax.inject.Inject

/**
 * Created by dfournier on 18/02/18.
 */
class MovieDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var viewModel: MovieDetailViewModel

    override fun getLayoutId(): Int = R.layout.fragment_detail

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        viewModel.state.observeSafe(this) {
            title.text = it.movie?.title
        }
    }

    override fun onResume() {
        super.onResume()
        arguments?.let {
            viewModel.start(it.getLong(ARG_MOVIE_ID))
        }
    }

    companion object {
        const val ARG_MOVIE_ID = "ARG_MOVIE_ID"

        fun newInstance(id: Long): MovieDetailFragment {
            val frag = MovieDetailFragment()
            frag.arguments = Bundle().apply {
                putLong(ARG_MOVIE_ID, id)
            }
            return frag
        }
    }
}