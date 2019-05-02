package io.redgreen.benchpress.login

import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import io.redgreen.benchpress.login.LoginEvent.EmailChanged
import org.junit.Test

class LoginLogicTest {
    @Test
    fun `user can enter email`() {
        val updateSpec = UpdateSpec<LoginModel, LoginEvent, LoginEffect>(LoginLogic)
        val blankModel = LoginModel.BLANK
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
}
