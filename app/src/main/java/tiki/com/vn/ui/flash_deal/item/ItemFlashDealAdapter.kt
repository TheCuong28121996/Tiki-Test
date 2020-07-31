package tiki.com.vn.ui.flash_deal.item

import android.view.ViewGroup
import tiki.com.vn.R
import tiki.com.vn.base.BaseAdapter
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.FlashDealEntity

class ItemFlashDealAdapter : BaseAdapter<FlashDealEntity, FlashDealEntity>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            BaseHolder<FlashDealEntity, FlashDealEntity> {

        return ItemFlashDealHolder(
            createView(R.layout.item_adapter_flash_deal, parent)
        )
    }
}