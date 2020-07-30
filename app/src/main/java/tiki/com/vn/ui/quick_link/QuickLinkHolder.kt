package tiki.com.vn.ui.quick_link

import android.view.View
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.other.ViewHolderListener

class QuickLinkHolder internal constructor(
    view: View,
    listener: ViewHolderListener<QuickLinkEntity>
) : BaseHolder<List<QuickLinkEntity>, QuickLinkEntity>(view, listener) {

    override fun bindData(data: List<QuickLinkEntity>?) {
        TODO("Not yet implemented")
    }
}