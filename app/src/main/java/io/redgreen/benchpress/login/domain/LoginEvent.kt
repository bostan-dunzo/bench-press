package io.redgreen.benchpress.login.domain

sealed class LoginEvent {
    data class EmailChanged(val email: String): LoginEvent()

    data class PasswordChanged(val password: String): LoginEvent()

    object LoginAttempted : LoginEvent()

    object LoginSucceeded : LoginEvent()

    object LoginFailed : LoginEvent()
}
