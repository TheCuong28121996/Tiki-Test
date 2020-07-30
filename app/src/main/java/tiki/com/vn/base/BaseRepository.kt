package tiki.com.vn.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tiki.com.vn.data.ResponseData

open class BaseRepository {

    suspend fun <T : Any> request(call: suspend () -> ResponseData<T>): ResponseData<T> {
        return withContext(Dispatchers.IO) { call.invoke() }.apply {
            when (errorCode) {
                100 -> throw TokenInvalidException()
            }
        }
    }

    class TokenInvalidException(msg: String = "token invalid") : Exception(msg)
}