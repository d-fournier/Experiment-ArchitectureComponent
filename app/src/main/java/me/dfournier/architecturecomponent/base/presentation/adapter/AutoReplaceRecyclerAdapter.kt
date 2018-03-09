package me.dfournier.architecturecomponent.base.presentation.adapter

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView


/**
 * https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/ui/common/DataBoundListAdapter.java
 * @author dfournier
 */
abstract class AutoReplaceRecyclerAdapter<T, VH : RecyclerView.ViewHolder?> : RecyclerView.Adapter<VH>() {

    private var list: List<T>? = null
    private var diffVersion = 0

    final override fun getItemCount(): Int = list?.size ?: 0

    final override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list?.get(position)
        onBindViewHolder(holder, item)
    }

    fun set(newList: List<T>?) {
        diffVersion++
        list = newList
        notifyDataSetChanged()
    }

    @SuppressLint("StaticFieldLeak")
    fun replace(newList: List<T>?) {
        diffVersion++
        val oldList = list
        if (oldList == null) {
            if (newList == null) {
                // Nothing changed
                return
            } else {
                list = newList
                notifyItemRangeInserted(0, newList.size)
            }
        } else if (newList == null) {
            val oldSize = oldList.size
            list = null
            notifyItemRangeRemoved(0, oldSize)
        } else {
            val diffCalculationVersion = diffVersion
            object : AsyncTask<Unit, Unit, DiffUtil.DiffResult>() {
                override fun doInBackground(vararg params: Unit?): DiffUtil.DiffResult {
                    return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                        override fun getOldListSize(): Int = oldList.size

                        override fun getNewListSize(): Int = newList.size

                        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                                areItemsTheSame(oldList[oldItemPosition], newList[newItemPosition])

                        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                                areContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])

                    })
                }

                override fun onPostExecute(result: DiffUtil.DiffResult?) {
                    if (diffCalculationVersion == diffVersion) {
                        list = newList
                        result!!.dispatchUpdatesTo(this@AutoReplaceRecyclerAdapter)
                    }
                }
            }.execute()
        }
    }

    protected open fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

    protected open fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

    abstract fun onBindViewHolder(holder: VH, item: T?)
}