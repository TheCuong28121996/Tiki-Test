package tiki.com.vn.ui.quick_link.item

import android.view.ViewGroup
import tiki.com.vn.R
import tiki.com.vn.base.BaseAdapter
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.QuickLinkEntity

class ItemQuickLinkAdapter: BaseAdapter<QuickLinkEntity, QuickLinkEntity>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<QuickLinkEntity, QuickLinkEntity> {
        return ItemQuickLinkHolder(createView(R.layout.item_quick_link, parent), getListener())
    }
}