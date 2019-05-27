package com.losingtimeapps.javahub.common.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.losingtimeapps.domain.Error
import com.losingtimeapps.presentation.R

abstract class BaseInjectingFragment : Fragment() {

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onAttach(context: Context) {
        onInject()

        super.onAttach(context)
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    abstract fun onInject()



}
