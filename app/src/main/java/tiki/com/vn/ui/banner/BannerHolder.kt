package tiki.com.vn.ui.banner

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.item_adapter_banner.view.*
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.BannerEntity
import tiki.com.vn.other.ViewHolderListener

class BannerHolder internal constructor(view: View, listener: ViewHolderListener<BannerEntity>): BaseHolder<List<BannerEntity>, BannerEntity>(view, listener) {

    override fun bindData(data: List<BannerEntity>?) {
        if (data != null) {
            showReponData(data)
        }else{
            itemView.viewFlipper.visibility = View.GONE
        }
    }

    private fun showReponData(data: List<BannerEntity>){
        if(itemView.viewFlipper != null){
            itemView.viewFlipper.setInAnimation(itemView.context, android.R.anim.slide_in_left)
            itemView.viewFlipper.setOutAnimation(itemView.context, android.R.anim.slide_out_right)

            for(item in data){
                val imageView = ImageView(itemView.context)
                val layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                layoutParams.gravity = Gravity.CENTER
                imageView.layoutParams = layoutParams

                Glide.with(itemView.context)
                    .load(item.image_url)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(imageView)

                imageView.setOnClickListener{
                    sendListener(item)
                }
                itemView.viewFlipper.addView(imageView)
            }
        }
        itemView.viewFlipper.visibility = View.VISIBLE
    }
}