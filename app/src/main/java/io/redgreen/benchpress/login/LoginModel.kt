package io.redgreen.benchpress.login

data class LoginModel(
    val email: Email,
    val password: String
) {
    companion object {
        val BLANK = LoginModel(Email(""), "")
    }

    fun emailChanged(email: String): LoginModel {
        return copy(email = Email(email))
    }

    fun passwordChanged(password: String): LoginModel {
        return copy(password = password)
    }
}
