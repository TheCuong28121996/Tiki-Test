package tiki.com.vn.ui.quick_link.item

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_quick_link.view.*
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.other.ViewHolderListener

class ItemQuickLinkHolder internal constructor(view: View, listener: ViewHolderListener<QuickLinkEntity>):
    BaseHolder<QuickLinkEntity, QuickLinkEntity>(view, listener){

    override fun bindData(data: QuickLinkEntity?) {
        if (data != null) {
            showReponData(data)
        } else {
            itemView.imgIcon.visibility = View.GONE
            itemView.tvTitle.visibility = View.GONE
        }
    }

    private fun showReponData(data: QuickLinkEntity){
        var iconVisibility = View.GONE

        if(!data.image_url.isEmpty()){
            Glide.with(itemView.context)
                .load(data.image_url)
                .into(itemView.imgIcon)
            iconVisibility = View.VISIBLE
        }
        itemView.imgIcon.visibility = iconVisibility

        var titleVisibility = View.GONE
        if(!data.title.isEmpty()){
            itemView.tvTitle.text = data.title
            titleVisibility = View.VISIBLE
        }
        itemView.tvTitle.visibility = titleVisibility

        itemView.imgIcon.setOnClickListener{
            sendListener(data)
        }
    }
}