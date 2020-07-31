package tiki.com.vn.ui.quick_link.inner

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import kotlinx.android.synthetic.main.inner_quick_link.view.*
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.other.ViewHolderListener
import tiki.com.vn.ui.quick_link.item.ItemQuickLinkAdapter

class InnerQuickLinkHolder internal constructor(view: View,
    listener: ViewHolderListener<QuickLinkEntity>
) :
    BaseHolder<List<QuickLinkEntity>, QuickLinkEntity>(view, listener) {

    private val mItemQuickLinkAdapter by lazy { ItemQuickLinkAdapter() }

    override fun bindData(data: List<QuickLinkEntity>?) {
        if (data != null) {
            showReponData(data)
        } else {
            itemView.recyclerInnerQuickLink.visibility = View.GONE
        }
    }

    private fun showReponData(data: List<QuickLinkEntity>){
        mItemQuickLinkAdapter.run {
            setOnClickListener(listener)
        }

        itemView.recyclerInnerQuickLink.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            itemAnimator = DefaultItemAnimator()
            adapter = mItemQuickLinkAdapter
            visibility = View.VISIBLE
        }

        mItemQuickLinkAdapter.addData(data)
    }

    private val listener = object : ViewHolderListener<QuickLinkEntity>{
        override fun itemClicked(data: QuickLinkEntity, positon: Int) {
            sendListener(data)
        }
    }
}