package io.redgreen.benchpress.login

sealed class LoginEvent {

    data class EmailChanged(val email: String): LoginEvent()
    data class PasswordChanged(val password: String): LoginEvent()

}
