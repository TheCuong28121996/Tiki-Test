package tiki.com.vn.data

data class FlashDealEntity(
    val status: Int,
    val url: String = "",
    val tags: String = "",
    val discount_percent: Int,
    val special_price: Int,
    val special_from_date: Double,
    val from_date: String = "",
    val special_to_date: Double,
    val progress: ProgressEntity,
    val product: ProductEntity
)