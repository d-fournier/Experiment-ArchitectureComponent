package me.dfournier.architecturecomponent.base.presentation.fragment

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by dfournier on 16/02/18.
 */
abstract class BaseBindingFragment<T : ViewDataBinding> : BaseFragment() {

    lateinit var binding: T

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        val view = binding.root
        view?.let { bindView(it) }
        return view
    }

}