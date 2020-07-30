package tiki.com.vn.data

data class QuickLinkEntity(
    val title: String = "",
    val content: String = "",
    val url: String = "",
    val authentication: Boolean,
    val web_url: String = "",
    val image_url: String = ""
)