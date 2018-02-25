package me.dfournier.architecturecomponent.util

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

/**
 * Created by dfournier on 01/03/18.
 */

fun <T> LiveData<T>.observeSafe(lifeCycleOwner: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(lifeCycleOwner, Observer {
        it?.let(observer)
    })
}