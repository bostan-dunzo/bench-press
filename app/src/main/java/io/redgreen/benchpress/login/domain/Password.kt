package io.redgreen.benchpress.login.domain

data class Password(val value: String) {
    fun isValid(): Boolean {
        return value.length > 7
    }


}
