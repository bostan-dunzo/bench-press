package io.redgreen.benchpress.login.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LoginModelTest {
    @Test
    fun `model with invalid email and password is not ready for login`() {
        val loginModel = LoginModel
            .BLANK
            .emailChanged("invalid-email")
            .passwordChanged("123")

        assertThat(loginModel.isReadyForLogin)
            .isFalse()
    }

    @Test
    fun `model with valid email and password is ready for login`() {
        val loginModel = LoginModel
            .BLANK
            .emailChanged("validEmailt@test.com")
            .passwordChanged("12345678")

        assertThat(loginModel.isReadyForLogin)
            .isTrue()
    }
}
