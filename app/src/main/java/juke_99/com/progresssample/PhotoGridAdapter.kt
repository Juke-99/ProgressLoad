package juke_99.com.progresssample

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.squareup.picasso.Picasso

class PhotoGridAdapter : RecycleBaseAdapter {
    private var context: Context? = null
    private var inflater: LayoutInflater? = null
    private var imageSize: Int = 0

    constructor(context: Context, items: ArrayList<Any>, spanCount: Int) : super(items) {
        this.context = context
        inflater = LayoutInflater.from(context)

        val margin: Int = context.resources.getDimensionPixelSize(R.dimen.unit_margin)
        val widthPixels: Int = context.resources.displayMetrics.widthPixels

        imageSize = (widthPixels - (spanCount - 1) * margin) / spanCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder

        if(viewType == TYPE_ITEM) {
            val view: FrameLayout = inflater!!.inflate(R.layout.photo, parent, false) as FrameLayout
            viewHolder = PhotoLayoutHolder(view)
        } else {
            val view: FrameLayout = inflater!!.inflate(R.layout.progress_bar, parent, false) as FrameLayout
            viewHolder = ProgressBarLayoutHolder(view)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PhotoLayoutHolder) {
            val photo: Photo = objects!!.get(position) as Photo
            val photoView: AppCompatImageView? = (holder as PhotoLayoutHolder).photoImageView
            Picasso.get().load(photo.drawableId).into(photoView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val any: Any = objects!!.get(position)

        if(any is ProgressBar) {
            return TYPE_PROG
        } else {
            return TYPE_ITEM
        }
    }

    class PhotoLayoutHolder : RecyclerView.ViewHolder {
        var photoImageView: AppCompatImageView? = null

        constructor(view: FrameLayout) : super(view) {
            photoImageView = view.findViewById(R.id.photo)
        }
    }

    class ProgressBarLayoutHolder :  RecyclerView.ViewHolder {
        var progressBarLayout: FrameLayout? = null

        constructor(view: FrameLayout) : super(view) {
            progressBarLayout = view
        }
    }
}