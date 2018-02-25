package me.dfournier.architecturecomponent.movie.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import me.dfournier.architecturecomponent.R
import me.dfournier.architecturecomponent.model.Movie

class MovieViewHolder(
        view: View,
        private val listener: (Long) -> Any
) : RecyclerView.ViewHolder(view) {

    val title: TextView = view.findViewById(R.id.title)

    init {
    }

    fun bind(movie: Movie) {
        itemView.setOnClickListener {
            listener(movie.id)
        }
        title.text = movie.title
    }

}