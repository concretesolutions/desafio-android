package sergio.com.br.desafioandroid.ui.activities

import android.arch.lifecycle.Observer
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.custom_toolbar_layout.*
import sergio.com.br.desafioandroid.R
import sergio.com.br.desafioandroid.api.APIUtils
import sergio.com.br.desafioandroid.ui.view_models.BaseViewModel

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var dialogProgress: SweetAlertDialog

    var isHomeActivity: Boolean = false

    open fun populateData() {}

    protected fun init() {
        populateData()
    }

    protected fun setCustomBar(title: String, isBack: Boolean) {
        titleText.text = title

        if (isBack) {
            backArrow.visibility = View.VISIBLE
            backArrow.setOnClickListener {
                finish()
                overridePendingTransition(R.anim.left_in, R.anim.right_out)
            }
        }
    }

    protected fun addAPIObservables(viewModel: BaseViewModel) {
        viewModel.onError.observe(this, Observer {
            APIUtils.errorResponse(this, it!!)
        })

        viewModel.onThrowable.observe(this, Observer {
            APIUtils.errorResponseWithThrowable(this, it!!)
        })

        viewModel.isLoading.observe(this, Observer {
            if (it!!) {
                showProgress()
            } else {
                hideProgress()
            }
        })
    }

    private fun initDialogProgress() {
        dialogProgress = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)

        dialogProgress.getProgressHelper().setBarColor(ContextCompat.getColor(this, R.color.progress_color))
        dialogProgress.setTitleText(getString(R.string.loading_text))
        dialogProgress.setCancelable(false)
    }

    protected fun showProgress() {
        if (!::dialogProgress.isInitialized) initDialogProgress()

        if (!dialogProgress.isShowing()) dialogProgress.show()
    }

    protected fun hideProgress() {
        if (!::dialogProgress.isInitialized) initDialogProgress()
        if (dialogProgress.isShowing()) dialogProgress.dismiss()
    }

    override fun onBackPressed() {
        if (!isHomeActivity) {
            super.onBackPressed()
            overridePendingTransition(R.anim.left_in, R.anim.right_out)
        }
    }
}