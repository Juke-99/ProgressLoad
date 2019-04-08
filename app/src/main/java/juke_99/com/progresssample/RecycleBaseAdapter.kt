package juke_99.com.progresssample

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlin.collections.ArrayList

class RecycleBaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
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

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}