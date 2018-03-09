package me.dfournier.architecturecomponent.base.presentation.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import me.dfournier.architecturecomponent.BR

/**
* @author dfournier
*/
class ViewBindingViewHolder(private val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Any) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }

}