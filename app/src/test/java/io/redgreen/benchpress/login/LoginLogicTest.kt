package io.redgreen.benchpress.login

import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import io.redgreen.benchpress.login.LoginEffect.AttemptLogin
import io.redgreen.benchpress.login.LoginEvent.*
import org.junit.Test

class LoginLogicTest {
    private val updateSpec = UpdateSpec<LoginModel, LoginEvent, LoginEffect>(LoginLogic)
    private val blankModel = LoginModel.BLANK

    @Test
    fun `user can enter email`() {
        val email = "test@gmail.com"

        updateSpec
            .given(blankModel)
            .`when`(EmailChanged(email))
            .then(
                assertThatNext(
                    hasModel(blankModel.emailChanged(email)),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `user can enter password`() {
        val password = "password"

        updateSpec
            .given(blankModel)
            .`when`(PasswordChanged(password))
            .then(
                assertThatNext(
                    hasModel(blankModel.passwordChanged(password)),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `user can attempt login`() {
        val readyForLoginModel = LoginModel
            .BLANK
            .emailChanged("test@gmail.com")
            .passwordChanged("super-secret")

        updateSpec
            .given(readyForLoginModel)
            .`when`(LoginAttempted)
            .then(
                assertThatNext(
                    hasModel(readyForLoginModel.attemptLogin()),
                    hasEffects(AttemptLogin("test@gmail.com", "super-secret") as LoginEffect)
                )
            )
    }
}
