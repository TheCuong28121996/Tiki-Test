package tiki.com.vn.ui.quick_link

import android.view.ViewGroup
import tiki.com.vn.R
import tiki.com.vn.base.BaseAdapter
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.QuickLinkEntity

class QuickLinkAdapter : BaseAdapter<List<QuickLinkEntity>, QuickLinkEntity>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<List<QuickLinkEntity>, QuickLinkEntity> {
        return QuickLinkHolder(
            createView(R.layout.item_adapter_quick_link, parent), getListener()
        )
    }
}