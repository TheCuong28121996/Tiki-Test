package tiki.com.vn.data

data class BannerEntity(
    val id: Int,
    val title: String = "",
    val content: String = "",
    val url: String = "",
    val image_url: String = "",
    val thumbnail_url: String = "",
    val mobile_url: String = "",
    val ratio: Double
)