package tiki.com.vn.ui.quick_link.inner
import android.view.ViewGroup
import tiki.com.vn.R
import tiki.com.vn.base.BaseAdapter
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.ui.quick_link.inner.InnerQuickLinkHolder

class InnerQuickLinkAdapter: BaseAdapter<List<QuickLinkEntity>, QuickLinkEntity>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<List<QuickLinkEntity>, QuickLinkEntity> {
        return InnerQuickLinkHolder(
            createView(
                R.layout.inner_quick_link,
                parent
            ), getListener()
        )
    }
}