package io.redgreen.benchpress.login.view

import io.redgreen.benchpress.login.domain.LoginModel

class LoginViewRenderer(
    private val view: LoginView
) {
    fun render(model: LoginModel) {
        if (model == LoginModel.BLANK || !model.isReadyForLogin) {
            view.disableSubmitButton()
        }
    }
}
