package me.dfournier.architecturecomponent

/**
 * Created by dfournier on 27/02/18.
 */
sealed class Event

data class ErrorEvent(
        val errorMessage: String
) : Event()

data class NavigationEvent(
        val tag: String,
        val info: Long? = null
) : Event()
