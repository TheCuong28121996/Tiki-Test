package tiki.com.vn.ui.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import tiki.com.vn.R
import tiki.com.vn.base.BaseActivity
import androidx.recyclerview.widget.MergeAdapter
import kotlinx.android.synthetic.main.activity_main.*
import tiki.com.vn.data.BannerEntity
import tiki.com.vn.other.ViewHolderListener
import tiki.com.vn.ui.banner.BannerAdapter

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private var isRefresh: Boolean = false
    private val mMergeAdapter = MergeAdapter()
    private val mBannerAdapter by lazy { BannerAdapter() }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        if (!this::viewModel.isInitialized) {
            viewModel = ViewModelProviders.of(this)
                .get(MainViewModel::class.java)
            setObserveLive(viewModel)
        }

        mBannerAdapter.run {
            setOnClickListener(listenerBanner)
            mMergeAdapter.addAdapter(0, mBannerAdapter)
        }

        recyclerHome.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            itemAnimator = DefaultItemAnimator()
            adapter = mMergeAdapter
        }

        swipeRefresh.setOnRefreshListener {
            isRefresh = true
            initData()
            swipeRefresh.isRefreshing = false
        }
    }

    override fun initData() {
        viewModel.getBanner()

        viewModel.getQuickLink()

        viewModel.getFlashDeal()
    }

    override fun startObserve() {
        viewModel.apply {
            _repoBanner.observe(this@MainActivity, Observer {
                if(it != null){
                    Log.d("Logger", "banner "+ it.size)
                    if(isRefresh){
                        mBannerAdapter.refreshItem(it)
                        isRefresh = false
                    }else{
                        mBannerAdapter.addData(it)
                    }

                }
            })

            _repoQuickLink.observe(this@MainActivity, Observer {
                if(it != null){
                    Log.d("Logger", "QuickLink "+ it.size)
                }
            })

            _repoFlashDeal.observe(this@MainActivity, Observer {
                if(it != null){
                    Log.d("Logger", "FlashDeal "+ it.size)
                }
            })
        }
    }

    private val listenerBanner = object: ViewHolderListener<BannerEntity>{
        override fun itemClicked(data: BannerEntity, positon: Int) {
            Log.d("Logger", "click banner ${data.id}")
        }
    }
}