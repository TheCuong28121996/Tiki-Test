package tiki.com.vn.ui.flash_deal

import android.view.ViewGroup
import tiki.com.vn.R
import tiki.com.vn.base.BaseAdapter
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.FlashDealEntity

class FlashDealAdapter: BaseAdapter<List<FlashDealEntity>, FlashDealEntity>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<List<FlashDealEntity>, FlashDealEntity> {
        return FlashDealHolder(
            createView(R.layout.flash_deal_adapter, parent)
        )
    }
}