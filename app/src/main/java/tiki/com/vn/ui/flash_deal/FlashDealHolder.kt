package tiki.com.vn.ui.flash_deal

import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import kotlinx.android.synthetic.main.flash_deal_adapter.view.*
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.FlashDealEntity
import tiki.com.vn.ui.flash_deal.item.ItemFlashDealAdapter

class FlashDealHolder internal constructor(view: View):
    BaseHolder<List<FlashDealEntity>, FlashDealEntity>(view){

    private val mAdapter by lazy { ItemFlashDealAdapter() }

    override fun bindData(data: List<FlashDealEntity>?) {
        if (data != null) {
            showReponData(data)
        } else {
            itemView.recyclerFlash.visibility = View.GONE
        }
    }

    private fun showReponData(data: List<FlashDealEntity>){
        mAdapter.run { }

        itemView.recyclerFlash.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
            visibility = View.VISIBLE
        }

        mAdapter.addData(data)
    }
}