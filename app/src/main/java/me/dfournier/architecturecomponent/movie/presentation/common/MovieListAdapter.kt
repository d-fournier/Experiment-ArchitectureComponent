package me.dfournier.architecturecomponent.movie.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.dfournier.architecturecomponent.R
import me.dfournier.architecturecomponent.model.Movie

/**
 * Created by dfournier on 16/02/18.
 */
class MovieListAdapter(val listener: (Long) -> Any) : RecyclerView.Adapter<MovieViewHolder>() {

    var list: List<Movie> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, listener)
    }
}
