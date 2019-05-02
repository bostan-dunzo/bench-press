package io.redgreen.benchpress.login.domain

data class Email(val value: String) {
    fun isValid(): Boolean {
        return value.indexOf('.') > value.indexOf('@')
    }
}
