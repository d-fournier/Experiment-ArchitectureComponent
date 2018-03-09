package me.dfournier.architecturecomponent.base.presentation.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @author dfournier
 */
abstract class ViewBindingRecyclerViewAdapter<T> : AutoReplaceRecyclerAdapter<T, ViewBindingViewHolder>() {


    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBindingViewHolder {
        return ViewBindingViewHolder(
                DataBindingUtil.inflate<ViewDataBinding>(
                        LayoutInflater.from(parent.context),
                        viewType,
                        parent,
                        false
                )
        )
    }

    final override fun onBindViewHolder(holder: ViewBindingViewHolder, item: T?) {
        if (item != null) {
            holder.bind(item)
        }
    }

    final override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    @LayoutRes
    abstract fun getLayoutIdForPosition(position: Int): Int
}