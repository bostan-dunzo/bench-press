package io.redgreen.benchpress.login

import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import io.redgreen.benchpress.login.LoginEvent.EmailChanged
import io.redgreen.benchpress.login.LoginEvent.PasswordChanged
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
}
