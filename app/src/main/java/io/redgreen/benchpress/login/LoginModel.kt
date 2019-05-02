package io.redgreen.benchpress.login

data class LoginModel(
    val email: Email,
    val password: Password
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
}
