package io.redgreen.benchpress.login.view

import io.redgreen.benchpress.architecture.AsyncOp.IN_FLIGHT
import io.redgreen.benchpress.login.domain.LoginModel

class LoginViewRenderer(
    private val view: LoginView
) {
    fun render(model: LoginModel) {
        if (model.loginAsyncOp == IN_FLIGHT) {
            view.disableEmailField()
            view.disablePasswordField()
            view.disableSubmitButton()
            view.showLoading()
            view.hideLoginFailedError()
        } else if (model == LoginModel.BLANK || !model.isReadyForLogin) {
            view.disableSubmitButton()
        } else if (model.isReadyForLogin) {
            view.enableSubmitButton()
        }
    }
}
