package me.dfournier.architecturecomponent.base.presentation.fragment

import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.View

/**
 * Created by dfournier on 16/02/18.
 */
abstract class BaseFragment : Fragment() {

    @CallSuper
    open fun bindView(view: View) {
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

}