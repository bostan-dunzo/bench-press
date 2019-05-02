package io.redgreen.benchpress.login

data class LoginModel(val email: String) {
    companion object {
        val BLANK = LoginModel("")
    }

    fun emailChanged(email: String): LoginModel {
        return copy(email = email)
    }
}
