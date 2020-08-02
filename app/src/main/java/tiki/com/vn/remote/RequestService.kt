package tiki.com.vn.remote

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import tiki.com.vn.data.BannerEntity
import tiki.com.vn.data.FlashDealEntity
import tiki.com.vn.data.QuickLinkEntity
import tiki.com.vn.data.ResponseData

interface RequestService {
//    @GET("v2/home/banners/v2")
//    suspend fun getBanner(): ResponseData<List<BannerEntity>>
//
//    @GET("shopping/v2/widgets/quick_link")
//    suspend fun getQuickLink(): ResponseData<List<List<QuickLinkEntity>>>
//
//    @GET("v2/widget/deals/hot")
//    suspend fun getFlashDeal(): ResponseData<List<FlashDealEntity>>

    @GET("v2/home/banners/v2")
    fun getBanner(): Observable<ResponseData<List<BannerEntity>>>

    @GET("shopping/v2/widgets/quick_link")
    fun getQuickLink(): Observable<ResponseData<List<List<QuickLinkEntity>>>>

    @GET("v2/widget/deals/hot")
    fun getFlashDeal(): Observable<ResponseData<List<FlashDealEntity>>>
}