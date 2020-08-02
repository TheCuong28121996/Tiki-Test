package tiki.com.vn.ui.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.MergeAdapter
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import tiki.com.vn.R
import tiki.com.vn.base.BaseActivity
import tiki.com.vn.data.BannerEntity
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.other.ViewHolderListener
import tiki.com.vn.ui.banner.BannerAdapter
import tiki.com.vn.ui.flash_deal.FlashDealAdapter
import tiki.com.vn.ui.quick_link.QuickLinkAdapter

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private val mMergeAdapter = MergeAdapter()
    private val mBannerAdapter by lazy { BannerAdapter() }
    private val mQuickLinkAdapter by lazy { QuickLinkAdapter() }
    private val mFlashDealAdapter by lazy { FlashDealAdapter() }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (Math.abs(verticalOffset)-appBarLayout!!.getTotalScrollRange() == 0) {
                    frameSearch.setPadding(25, 10, 200, 0)
                }
                else {
                    frameSearch.setPadding(25, 10, 25, 0)
                }
            }
        })

        if (!this::viewModel.isInitialized) {
            viewModel = ViewModelProviders.of(this)
                .get(MainViewModel::class.java)
            setObserveLive(viewModel)
        }

        mBannerAdapter.run {
            setOnClickListener(listenerBanner)
        }

        mQuickLinkAdapter.run {
            setOnClickListener(listenerQuick)
        }

        mFlashDealAdapter.run {}

        recyclerHome.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            itemAnimator = DefaultItemAnimator()
            adapter = mMergeAdapter
        }

        swRefresh.setOnRefreshListener {
            initData()
            swRefresh.isRefreshing = false
        }
    }

    override fun initData() {

        viewModel.getDataParallely()
    }

    override fun startObserve() {
        viewModel.apply {
            _repoBanner.observe(this@MainActivity, Observer {
                if(it != null){
                    Log.d("Logger", "banner "+ it.size)
                    mMergeAdapter.removeAdapter(mBannerAdapter)
                    mBannerAdapter.addNewItem(it)
                    mMergeAdapter.addAdapter(0, mBannerAdapter)
                }
            })

            _repoQuickLink.observe(this@MainActivity, Observer {
                if(it != null){
                    Log.d("Logger", "QuickLink "+ it.size)
                    mMergeAdapter.removeAdapter(mQuickLinkAdapter)
                    mQuickLinkAdapter.addNewItem(it)
                    mMergeAdapter.addAdapter(1, mQuickLinkAdapter)
                }
            })

            _repoFlashDeal.observe(this@MainActivity, Observer {
                if(it != null){
                    Log.d("Logger", "FlashDeal "+ it.size)
                    mMergeAdapter.removeAdapter(mFlashDealAdapter)
                    mFlashDealAdapter.addNewItem(it)
                    mMergeAdapter.addAdapter(2, mFlashDealAdapter)
                }
            })
        }
    }

    private val listenerBanner = object: ViewHolderListener<BannerEntity>{
        override fun itemClicked(data: BannerEntity, positon: Int) {
            Log.d("Logger", "click banner ${data.id}")
        }
    }

    private val listenerQuick = object :ViewHolderListener<QuickLinkEntity>{
        override fun itemClicked(data: QuickLinkEntity, positon: Int) {
            Log.d("Logger", "click quick Llink ${data.title}")
        }
    }
}