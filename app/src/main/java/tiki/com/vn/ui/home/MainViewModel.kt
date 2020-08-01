package tiki.com.vn.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.withContext
import tiki.com.vn.base.BaseViewModel
import tiki.com.vn.data.BannerEntity
import tiki.com.vn.data.FlashDealEntity
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.remote.Repository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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

    fun getBanner() = launchOnUI{
        val result = repository.getBanner()
        _repoBanner.value = result.data
    }

    fun getQuickLink() = launchOnUI{
        val result = repository.getQuickLink()
        _repoQuickLink.value = result.data
    }

    suspend fun getBanerQuickLink(){
        withContext(IO){

            val getData = launch{
                val banner = repository.getBanner()
                val quickLink = repository.getQuickLink()
                launchOnUI {
                    _repoBanner.value = banner.data
                    _repoQuickLink.value = quickLink.data
                }
            }
            getData.join()

            async {
                val flashDetail = repository.getFlashDeal()
                launchOnUI {
                    _repoFlashDeal.value = flashDetail.data
                }
            }.await()
        }
    }

    fun getFlashDeal() = launchOnUI{
        val result = repository.getFlashDeal()
        _repoFlashDeal.value = result.data
    }
}