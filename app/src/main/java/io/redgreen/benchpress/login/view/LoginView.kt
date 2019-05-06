package io.redgreen.benchpress.login.view

interface LoginView {
    fun disableSubmitButton()
    fun enableSubmitButton()
    fun disableEmailField()
    fun disablePasswordField()
    fun showLoading()
    fun hideLoginFailedError()
    fun hideLoading()
    fun showLoginFailedError()
    fun enableEmail()
    fun enablePassword()
}
