package tiki.com.vn.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tiki.com.vn.base.BaseViewModel
import tiki.com.vn.data.BannerEntity
import tiki.com.vn.data.FlashDealEntity
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.data.ResponseData
import tiki.com.vn.remote.Repository
import tiki.com.vn.remote.RetrofitClient
import tiki.com.vn.utils.Executor
import java.util.concurrent.atomic.AtomicBoolean

class MainViewModel : BaseViewModel() {

    private val repoBanner = MutableLiveData<List<BannerEntity>>()
    private val repoQuickLink = MutableLiveData<List<List<QuickLinkEntity>>>()
    private val repoFlashDeal = MutableLiveData<List<FlashDealEntity>>()

    val _repoBanner: MutableLiveData<List<BannerEntity>>
        get() = repoBanner

    val _repoQuickLink: MutableLiveData<List<List<QuickLinkEntity>>>
        get() = repoQuickLink

    val _repoFlashDeal: MutableLiveData<List<FlashDealEntity>>
        get() = repoFlashDeal

    private fun getBanner() {
        RetrofitClient.reqApi.getBanner()
            .enqueue(object : Callback<ResponseData<List<BannerEntity>>>{

                override fun onFailure(call: Call<ResponseData<List<BannerEntity>>>, t: Throwable) {
                    Log.d("Logger", "error->$t.message)")
                }

                override fun onResponse(
                    call: Call<ResponseData<List<BannerEntity>>>,
                    response: Response<ResponseData<List<BannerEntity>>>) {

                   _repoBanner.value = response.body()!!.data
                }

            })
    }

    private fun getQuickLink() {
        RetrofitClient.reqApi.getQuickLink()
            .enqueue(object: Callback<ResponseData<List<List<QuickLinkEntity>>>>{
                override fun onFailure(
                    call: Call<ResponseData<List<List<QuickLinkEntity>>>>,
                    t: Throwable
                ) {
                    Log.d("Logger", "error->$t.message)")
                }

                override fun onResponse(
                    call: Call<ResponseData<List<List<QuickLinkEntity>>>>,
                    response: Response<ResponseData<List<List<QuickLinkEntity>>>>
                ) {
                    _repoQuickLink.value = response.body()!!.data
                }

            })
    }

    private fun getFlashDeal(){
        RetrofitClient.reqApi.getFlashDeal()
            .enqueue(object :Callback<ResponseData<List<FlashDealEntity>>>{
                override fun onFailure(
                    call: Call<ResponseData<List<FlashDealEntity>>>,
                    t: Throwable
                ) {
                    Log.d("Logger", "error->$t.message)")
                }

                override fun onResponse(
                    call: Call<ResponseData<List<FlashDealEntity>>>,
                    response: Response<ResponseData<List<FlashDealEntity>>>
                ) {
                    _repoFlashDeal.value = response.body()!!.data
                }
            } )
    }

    var processing: AtomicBoolean = AtomicBoolean(true)
    fun getData(){
        Executor.Builder()
            .add(
               Runnable {
                   Log.d("Logger","TASK getBanner Start")
                   getQuickLink()
               }
            )
            .add(Runnable {
                Log.d("Logger","TASK getQuickLink Start")
                getBanner()
            })
            .callback(object : Executor.Callback{
                override fun onComplete() {
                    processing.set(false)
                    getFlashDeal()
                }
            })
            .build()
            .execute()
    }
}