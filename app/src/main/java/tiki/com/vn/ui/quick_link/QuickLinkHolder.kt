package tiki.com.vn.ui.quick_link

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import kotlinx.android.synthetic.main.item_adapter_quick_link.view.*
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.other.ViewHolderListener
import tiki.com.vn.ui.quick_link.inner.InnerQuickLinkAdapter

class QuickLinkHolder internal constructor(
    view: View,
    listener: ViewHolderListener<QuickLinkEntity>
) :
    BaseHolder<List<QuickLinkEntity>, QuickLinkEntity>(view, listener) {

    private val mInnerQuickLinkAdapter by lazy { InnerQuickLinkAdapter() }

    override fun bindData(data: List<QuickLinkEntity>?) {
        if (data != null) {
            showReponData(data)
        } else {
            itemView.recyclerQuickLink.visibility = View.GONE
        }
    }

    private fun showReponData(data: List<QuickLinkEntity>) {

        mInnerQuickLinkAdapter.run {
            setOnClickListener(listener)
        }

        itemView.recyclerQuickLink.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            itemAnimator = DefaultItemAnimator()
            adapter = mInnerQuickLinkAdapter
            visibility = View.VISIBLE
        }

        mInnerQuickLinkAdapter.addNewItem(data)
    }

    private val listener = object : ViewHolderListener<QuickLinkEntity> {
        override fun itemClicked(data: QuickLinkEntity, positon: Int) {
            sendListener(data)
        }
    }
}