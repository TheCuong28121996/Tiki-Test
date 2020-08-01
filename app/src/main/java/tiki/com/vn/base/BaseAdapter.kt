package tiki.com.vn.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tiki.com.vn.other.ViewHolderListener

abstract class BaseAdapter<T, O>: RecyclerView.Adapter<BaseHolder<T, O>>() {

    private var dataSource: MutableList<T> = ArrayList()

    private lateinit var listener: ViewHolderListener<O>

    open fun setOnClickListener(listener: ViewHolderListener<O>) {
        this.listener = listener
    }

    open fun getListener(): ViewHolderListener<O> {
        return this.listener
    }

    open fun addNewItem(data: Collection<T>){
        this.dataSource.clear()
        this.dataSource.addAll(data)
        notifyDataSetChanged()
    }

    open fun addNewItem(data: T) {
        this.dataSource.clear()
        this.dataSource.add(data)
        notifyItemInserted(this.dataSource.size)
    }

    open fun getItemAt(position: Int): T? {
        if (position >= 0 && (itemCount > position)) {
            return this.dataSource.get(position)
        }
        return null
    }

    open fun clear(){
        this.dataSource.clear()
        notifyDataSetChanged()
    }

    fun createView(resource: Int, parent: ViewGroup, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(parent.context).inflate(resource, parent, attachToRoot)
    }

    override fun onBindViewHolder(holder: BaseHolder<T, O>, position: Int) {
        val item = getItemAt(position)
        holder.bindData(item)
    }

    override fun getItemCount(): Int {
        return this.dataSource.size
    }
}