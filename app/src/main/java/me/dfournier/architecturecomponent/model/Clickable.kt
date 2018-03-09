package me.dfournier.architecturecomponent.model

/**
 * @author dfournier
 */
interface Clickable<T : Number> {
    val id: T

    val onItemSelected: (T) -> Unit
}