package tiki.com.vn.data

data class ProductEntity(
    val id: Int,
    val name: String = "",
    val url_path: String = "",
    val price: Double,
    val list_price: Double,
    val discount: Double,
    val thumbnail_url: String = ""
)