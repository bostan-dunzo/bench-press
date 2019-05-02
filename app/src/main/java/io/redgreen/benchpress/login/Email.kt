package io.redgreen.benchpress.login

data class Email(val value: String) {
    fun isValid(): Boolean {
        return value.indexOf('.') > value.indexOf('@')
    }
}
