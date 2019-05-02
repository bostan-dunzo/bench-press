package io.redgreen.benchpress.login

sealed class LoginEffect {
    data class AttemptLogin(val email: String, val password: String) : LoginEffect()

    object GotoHome : LoginEffect()
}
