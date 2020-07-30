package tiki.com.vn.base

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tiki.com.vn.utils.CommonUtils

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var mProgressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        initView()

        initData()

        startObserve()
    }

    open fun getLayoutResId(): Int = 0

    abstract fun initView()

    abstract fun initData()

    abstract fun startObserve()

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showLoadingDialog() {
        hideLoadingDialog()
        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    fun hideLoadingDialog() {
        if (this::mProgressDialog.isInitialized && mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }

    fun setObserveLive(viewModel: BaseViewModel) {

        viewModel.eventMessage.observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                showMessage(it.peekContent())
            }
        })

        viewModel.eventLoading.observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                if (it.getContentIfNotHandled() != null) {
                    if (it.peekContent()) {
                       showLoadingDialog()
                    } else {
                        hideLoadingDialog()
                    }
                }
            }
        })
    }
}