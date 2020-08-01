package tiki.com.vn.ui.home

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

    suspend fun getData(){
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
}