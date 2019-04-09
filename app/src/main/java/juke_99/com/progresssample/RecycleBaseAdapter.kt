package juke_99.com.progresssample

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlin.collections.ArrayList

abstract class RecycleBaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    companion object {
        val TYPE_ITEM = 1
        val TYPE_PROG = 2
    }

    private var lock: Any = Any()
    var objects: ArrayList<Any>? = null

    constructor(objects: ArrayList<Any>) {
        this.objects = objects
    }

    fun add(any: Any) {
        val position: Int = objects!!.size
        synchronized(lock) {
            objects!!.add(any)
        }

        notifyItemInserted(position)
    }

    fun remove(any: Any) {
        val position: Int = objects!!.indexOf(any)
        synchronized(lock) {
            objects!!.remove(any)
        }

        notifyItemRemoved(position)
    }

    fun clear() {
        synchronized(lock) {
            objects!!.clear()
        }

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return objects!!.size
    }
}