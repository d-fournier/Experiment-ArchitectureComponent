package me.dfournier.architecturecomponent.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by dfournier on 16/02/18.
 */
abstract class BaseFragment : Fragment() {


    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutId(), container, false)
        view?.let { bindView(it) }
        return view
    }

    @CallSuper
    open fun bindView(view: View) {}

    @LayoutRes
    abstract fun getLayoutId(): Int

}