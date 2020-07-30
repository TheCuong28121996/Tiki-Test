package tiki.com.vn.ui.quick_link

import android.view.ViewGroup
import tiki.com.vn.R
import tiki.com.vn.base.BaseAdapter
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.QuickLinkEntity

class InnerQuickLinkAdapter: BaseAdapter<QuickLinkEntity, QuickLinkEntity>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<QuickLinkEntity, QuickLinkEntity> {
        return InnerQuickLinkHolder(
            createView(R.layout.item_adapter_inner_quick, parent), getListener()
        )
    }
}