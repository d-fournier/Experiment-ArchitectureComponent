package me.dfournier.architecturecomponent.base.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by dfournier on 16/02/18.
 */
abstract class BaseClassicFragment : BaseFragment() {

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutId(), container, false)
        view?.let { bindView(it) }
        return view
    }

}