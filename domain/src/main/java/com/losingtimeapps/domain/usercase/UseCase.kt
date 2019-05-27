package com.losingtimeapps.domain.usercase

import com.losingtimeapps.domain.boundary.BaseScheduler

open class UseCase(private val baseScheduler: BaseScheduler) {

    fun uiInvoke() = baseScheduler.ui()

    fun computationInvoke() = baseScheduler.computation()

    fun ioInvoke() = baseScheduler.io()
}