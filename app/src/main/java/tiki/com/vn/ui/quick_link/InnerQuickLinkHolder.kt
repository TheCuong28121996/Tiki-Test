package tiki.com.vn.ui.quick_link

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_adapter_inner_quick.view.*
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.other.ViewHolderListener

class InnerQuickLinkHolder internal constructor(view: View, listener: ViewHolderListener<QuickLinkEntity>)
    : BaseHolder<QuickLinkEntity, QuickLinkEntity>(view, listener) {

    override fun bindData(data: QuickLinkEntity?) {
        if (data != null) {
            showReponData(data)
        }else{
            itemView.imgQuick.visibility = View.GONE
        }
    }

    private fun showReponData(data: QuickLinkEntity){
        Glide.with(itemView.context)
            .load(data.image_url)
            .into( itemView.imgQuick)

        itemView.imgQuick.apply {
            visibility = View.VISIBLE
            setOnClickListener{
                sendListener(data)
            }
        }
    }
}