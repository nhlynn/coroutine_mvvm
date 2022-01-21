package com.linnitsolution.coroutine_mvvm.view

interface BaseView {
    fun showError(message: String?)
    fun dismiss()
    fun showProgress()
}