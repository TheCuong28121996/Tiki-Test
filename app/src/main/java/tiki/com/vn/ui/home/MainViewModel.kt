package tiki.com.vn.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.withContext
import tiki.com.vn.base.BaseViewModel
import tiki.com.vn.data.BannerEntity
import tiki.com.vn.data.FlashDealEntity
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.remote.Repository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import tiki.com.vn.base.BaseRepository
import tiki.com.vn.data.ResponseData
import java.lang.Exception

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

//    suspend fun getData(){
//        withContext(IO){
//
//            val getData = launch{
//                val banner = repository.getBanner()
//                val quickLink = repository.getQuickLink()
//                launchOnUI {
//                    _repoBanner.value = banner.data
//                    _repoQuickLink.value = quickLink.data
//                }
//            }
//            getData.join()
//
//            async {
//                val flashDetail = repository.getFlashDeal()
//                launchOnUI {
//                    _repoFlashDeal.value = flashDetail.data
//                }
//            }.await()
//        }
//    }


    //The function getData() executes getBanner, getQuickLink parallel since they are wrapped within async{}
    // which returns Deferred<T> and we are using await() method to return the result of individual coroutines created by Async
    fun getData() {
        viewModelScope.launch {
            // A supervisorScope won’t cancel other children when one of them fails
            supervisorScope {
                val bannerResponse: ResponseData<List<BannerEntity>>?
                val quickLinkResponse: ResponseData<List<List<QuickLinkEntity>>>?

                val getBanner = async { repository.getBanner() }
                val getQuickLink = async { repository.getQuickLink() }

                //when a subsequent api call Fails
                bannerResponse = try {
                    getBanner.await()
                } catch (ex: Exception) {
                    null
                }

                //when a subsequent api call Fails
                quickLinkResponse = try {
                    getQuickLink.await()
                } catch (ex: Exception) {
                    null
                }

                // Post data UI
                launchOnUI {
                    if (bannerResponse != null) {
                        _repoBanner.value = bannerResponse.data
                    }

                    if (quickLinkResponse != null) {
                        _repoQuickLink.value = quickLinkResponse.data
                    }
                }

                async {
                    val flashDetail = repository.getFlashDeal()
                    launchOnUI {
                        _repoFlashDeal.value = flashDetail.data
                    }
                }.await()
            }
        }
    }
}