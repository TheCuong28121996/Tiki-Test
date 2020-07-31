package tiki.com.vn.ui.flash_deal.item

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_adapter_flash_deal.view.*
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.FlashDealEntity

class ItemFlashDealHolder internal constructor(view: View) :
    BaseHolder<FlashDealEntity, FlashDealEntity>(view) {
    override fun bindData(data: FlashDealEntity?) {
        if (data != null) {
            showReponData(data)
        } else {
            itemView.imgItem.visibility = View.GONE
            itemView.relativePercent.visibility = View.GONE
            itemView.tvPrice.visibility = View.GONE
            itemView.tvSold.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showReponData(data: FlashDealEntity){

        var imgProductVisibility = View.GONE
        if(!data.product.thumbnail_url.isEmpty()){
            Glide.with(itemView.context)
                .load(data.product.thumbnail_url)
                .into(itemView.imgItem)
            imgProductVisibility = View.VISIBLE
        }
        itemView.imgItem.visibility = imgProductVisibility

        var percentVisibility = View.GONE
        if(data.discount_percent != null){
            itemView.tvDiscount.text = "-${data.discount_percent}%"
            percentVisibility = View.VISIBLE
        }
        itemView.tvDiscount.visibility = percentVisibility

        var priceVisibility = View.GONE
        if(data.product.price != null){
            itemView.tvPrice.text = "${data.product.price}đ"
            priceVisibility = View.VISIBLE
        }
        itemView.tvPrice.visibility = priceVisibility

        var qtyOrderedVisibility = View.GONE
        if(data.progress.qty_ordered != null){
            itemView.tvSold.text = "Đã bán ${data.progress.qty_ordered}"
            qtyOrderedVisibility = View.VISIBLE
        }
        itemView.tvSold.visibility = qtyOrderedVisibility
    }
}