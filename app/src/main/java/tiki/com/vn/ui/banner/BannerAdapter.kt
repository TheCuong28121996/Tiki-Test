package tiki.com.vn.ui.banner

import android.view.ViewGroup
import tiki.com.vn.R
import tiki.com.vn.base.BaseAdapter
import tiki.com.vn.base.BaseHolder
import tiki.com.vn.data.BannerEntity

class BannerAdapter : BaseAdapter<List<BannerEntity>, BannerEntity>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            BaseHolder<List<BannerEntity>, BannerEntity> {

        return BannerHolder(
            createView(R.layout.item_adapter_banner, parent), getListener()
        )
    }
}