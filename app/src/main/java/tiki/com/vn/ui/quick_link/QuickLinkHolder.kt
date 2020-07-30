package tiki.com.vn.ui.quick_link

import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.item_adapter_quick_link.view.*
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.other.ViewHolderListener

class QuickLinkHolder internal constructor(view: View, listener: ViewHolderListener<QuickLinkEntity>)
    : BaseHolder<List<QuickLinkEntity>, QuickLinkEntity>(view, listener) {

    private val mAdapter by lazy { InnerQuickLinkAdapter() }

    override fun bindData(data: List<QuickLinkEntity>?) {
        if (data != null) {
            showReponData(data)
        }else{
            itemView.recyclerQuick.visibility = View.GONE
        }
    }

    private fun showReponData(data: List<QuickLinkEntity>){
        itemView.recyclerQuick.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
            visibility = View.VISIBLE
        }

        mAdapter.run {
            setOnClickListener(listener)
            mAdapter.addData(data)
        }
    }

    private val listener = object : ViewHolderListener<QuickLinkEntity>{
        override fun itemClicked(data: QuickLinkEntity, positon: Int) {
            sendListener(data)
        }
    }
}