package tiki.com.vn.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import tiki.com.vn.base.BaseViewModel
import tiki.com.vn.data.BannerEntity
import tiki.com.vn.data.FlashDealEntity
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.remote.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import tiki.com.vn.data.ResponseData
import tiki.com.vn.remote.RetrofitClient
import java.lang.Exception
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.flowable.FlowableReplay.observeOn
import tiki.com.vn.utils.DisposableManager

class MainViewModel : BaseViewModel() {

    private val repository = Repository()
    private val repoBanner = MutableLiveData<List<BannerEntity>>()
    private val repoQuickLink = MutableLiveData<List<List<QuickLinkEntity>>>()
    private val repoFlashDeal = MutableLiveData<List<FlashDealEntity>>()

    val _repoBanner: MutableLiveData<List<BannerEntity>>
        get() = repoBanner

    val _repoQuickLink: MutableLiveData<List<List<QuickLinkEntity>>>
        get() = repoQuickLink

    val _repoFlashDeal: MutableLiveData<List<FlashDealEntity>>
        get() = repoFlashDeal

    //The function getData() executes getBanner, getQuickLink parallel since they are wrapped within async{}
    // which returns Deferred<T> and we are using await() method to return the result of individual coroutines created by Async
//    fun getData() {
//        viewModelScope.launch {
//            // A supervisorScope won’t cancel other children when one of them fails
//            supervisorScope {
//                val bannerResponse: ResponseData<List<BannerEntity>>?
//                val quickLinkResponse: ResponseData<List<List<QuickLinkEntity>>>?
//
//                val getBanner = async { repository.getBanner() }
//                val getQuickLink = async { repository.getQuickLink() }
//
//                //when a subsequent api call Fails
//                bannerResponse = try {
//                    getBanner.await()
//                } catch (ex: Exception) {
//                    null
//                }
//
//                //when a subsequent api call Fails
//                quickLinkResponse = try {
//                    getQuickLink.await()
//                } catch (ex: Exception) {
//                    null
//                }
//
//                // Post data UI
//                launchOnUI {
//                    if (bannerResponse != null) {
//                        _repoBanner.value = bannerResponse.data
//                    }
//
//                    if (quickLinkResponse != null) {
//                        _repoQuickLink.value = quickLinkResponse.data
//                    }
//                }
//
//                async {
//                    val flashDetail = repository.getFlashDeal()
//                    launchOnUI {
//                        _repoFlashDeal.value = flashDetail.data
//                    }
//                }.await()
//            }
//        }
//    }

    fun getDataParallely() {
        DisposableManager.add(
            Observable.zip(
                RetrofitClient.reqApi.getBanner().subscribeOn(Schedulers.io()),
                RetrofitClient.reqApi.getQuickLink().subscribeOn(Schedulers.io()),

                BiFunction { bannerResponse: ResponseData<List<BannerEntity>>,
                             quickLinkResponsee: ResponseData<List<List<QuickLinkEntity>>> ->
                    combineResult(bannerResponse, quickLinkResponsee)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        Log.d("Logger", "result->$result")
                    },
                    { error ->
                        Log.d("Logger", "error->$error.message)")
                    }
                )
        )
    }

    private fun combineResult(
        bannerResponse: ResponseData<List<BannerEntity>>,
        quickLinkResponse: ResponseData<List<List<QuickLinkEntity>>>) {

        launchOnUI {
            _repoBanner.value = bannerResponse.data
            _repoQuickLink.value = quickLinkResponse.data
        }

        flashDeal()
    }

    private fun flashDeal() {
        DisposableManager.add(
            RetrofitClient.reqApi.getFlashDeal()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { flashDealResponse ->
                        launchOnUI {
                            _repoFlashDeal.value = flashDealResponse.data
                        }
                    },
                    { error -> Log.d("Logger", "error->$error.message)") }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        DisposableManager.dispose()
    }
}