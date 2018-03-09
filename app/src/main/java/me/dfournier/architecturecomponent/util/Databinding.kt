package me.dfournier.architecturecomponent.util

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import me.dfournier.architecturecomponent.model.Clickable

/**
 * @author dfournier
 */
@BindingAdapter("onRefreshListener")
fun onRefreshListener(view: SwipeRefreshLayout, listener: () -> Unit) {
    view.setOnRefreshListener(listener)
}

@BindingAdapter("onClickListener")
fun <T : Number> onClickListener(view: View, listener: Clickable<T>) {
    view.setOnClickListener {
        listener.onItemSelected(listener.id)
    }
}