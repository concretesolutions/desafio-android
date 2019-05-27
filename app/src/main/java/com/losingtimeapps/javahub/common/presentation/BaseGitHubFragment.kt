package com.losingtimeapps.javahub.common.presentation

import android.content.Context
import android.view.View
import android.widget.Toast
import com.losingtimeapps.common.BaseView
import com.losingtimeapps.domain.Error
import com.losingtimeapps.javahub.R
import kotlinx.android.synthetic.main.fragment_repository.*

abstract class BaseGitHubFragment : BaseInjectingFragment(), BaseView {

    override val layoutId: Int
        get() = R.layout.fragment_repository

    override fun showSnackbarError(error: Error) {
        Toast.makeText(activity, errorHandled(error), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(state: Boolean) {
        progress_bar.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun getContext(): Context {
        return activity!!.applicationContext
    }

    protected fun errorHandled(error: Error): String {
        return when (error) {
            Error.NetworkConnectionError -> this.getString(R.string.error_network_connection)
            Error.NotFoundError -> this.getString(R.string.error_not_found)
            Error.InternalServerError -> this.getString(R.string.error_internal_server)
            Error.EmptyLanguageError -> this.getString(R.string.error_empty_language)
            Error.RepositoryInvalidLanguageError -> this.getString(R.string.error_invalid_language)
            Error.EmptyRepositoryNameError -> this.getString(R.string.error_empty_repository)
            else -> this.getString(R.string.error_unexpected)
        }
    }
    override fun notDataLoaded(state:Boolean) {
        tv_no_data.visibility = if (state) View.VISIBLE else View.GONE
    }
}