package io.redgreen.benchpress.login.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PasswordTest {
    @Test
    fun `empty password is invalid`() {
        val password = Password("")

        assertThat(password.isValid())
            .isFalse()
    }

    @Test
    fun `short passwords are invalid`() {
        val inValidPassword = Password("123")

        assertThat(inValidPassword.isValid())
            .isFalse()
    }

    @Test
    fun `password with at least 8 characters are valid`() {
        val validPassword = Password("12345678")

        assertThat(validPassword.isValid())
            .isTrue()
    }

    @Test
    fun `passwords with more than 8 characters are valid`() {

        val validPassword = Password("123456789")

        assertThat(validPassword.isValid())
            .isTrue()
    }
}