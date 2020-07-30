package tiki.com.vn.data

data class ResponseData<out T>(val errorCode: Int, val errorMsg: String, val data: T)