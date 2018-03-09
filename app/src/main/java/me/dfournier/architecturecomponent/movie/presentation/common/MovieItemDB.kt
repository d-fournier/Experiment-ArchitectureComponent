package me.dfournier.architecturecomponent.movie.presentation.common

import me.dfournier.architecturecomponent.model.Clickable

/**
 * Created by dfournier on 06/03/18.
 */
data class MovieItemDB(
        override val id: Long,
        val title: String,
        override val onItemSelected: (Long) -> Unit
) : Clickable<Long>