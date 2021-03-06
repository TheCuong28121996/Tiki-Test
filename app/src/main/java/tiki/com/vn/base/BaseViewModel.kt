package tiki.com.vn.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tiki.com.vn.utils.Event

open class BaseViewModel : ViewModel() {

    val eventLoading = MutableLiveData<Event<Boolean>>()
    val eventMessage = MutableLiveData<Event<String>>()

    fun showLoading(value: Boolean) {
        eventLoading.value = Event(value)
    }

    fun showMessage(message: String) {
        eventMessage.value = Event(message)
    }

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) {
            block
        }
    }
}