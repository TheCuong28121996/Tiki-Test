package tiki.com.vn.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import tiki.com.vn.other.ViewHolderListener

abstract class BaseHolder<T, O>: RecyclerView.ViewHolder {

    constructor(itemView: View): super(itemView)

    constructor(itemView: View, listener: ViewHolderListener<O>): super(itemView){
        this.listener = listener
    }

    private var listener: ViewHolderListener<O>? = null

    abstract fun bindData(data: T?)

    open fun sendListener(data: O){
        this.listener!!.itemClicked(data, adapterPosition)
    }
}