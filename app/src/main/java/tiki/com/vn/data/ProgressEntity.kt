package tiki.com.vn.data

data class ProgressEntity(
    val qty: Int,
    val qty_ordered: Int,
    val qty_remain: Int,
    val percent: Double,
    val status: String
)