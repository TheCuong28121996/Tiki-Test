package tiki.com.vn.utils

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

object DisposableManager {
    private val TAG = DisposableManager::class.java.simpleName
    private var compositeDisposable: CompositeDisposable? = null
    fun add(disposable: Disposable?) {
        Log.e(TAG, "add")
        getCompositeDisposable()!!.add(disposable!!)
    }

    fun dispose() {
        Log.e(TAG, "dispose")
        getCompositeDisposable()!!.dispose()
    }

    private fun getCompositeDisposable(): CompositeDisposable? {
        if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        return compositeDisposable
    }
}