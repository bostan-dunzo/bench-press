package io.redgreen.benchpress.login

import io.redgreen.benchpress.architecture.AsyncOp
import io.redgreen.benchpress.architecture.AsyncOp.*

data class LoginModel(
    val email: Email,
    val password: Password,
    val loginAsyncOp: AsyncOp = IDLE
) {
    val isReadyForLogin: Boolean
        get() = email.isValid() && password.isValid()

    companion object {
        val BLANK = LoginModel(Email(""), Password(""))
    }

    fun emailChanged(email: String): LoginModel {
        return copy(email = Email(email))
    }

    fun passwordChanged(password: String): LoginModel {
        return copy(password = Password(password))
    }

    fun attemptLogin(): LoginModel {
        return copy(loginAsyncOp = IN_FLIGHT)
    }

    fun loginSuccessful(): LoginModel {
        return copy(loginAsyncOp = SUCCEEDED)
    }

    fun loginFailed(): LoginModel {
        return copy(loginAsyncOp = FAILED)
    }
}
