package io.redgreen.benchpress.login.effecthandler

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.redgreen.benchpress.login.domain.LoginEffect.AttemptLogin
import io.redgreen.benchpress.login.domain.LoginEffect.GotoHome
import io.redgreen.benchpress.login.domain.LoginEvent.LoginFailed
import io.redgreen.benchpress.login.domain.LoginEvent.LoginSucceeded
import io.redgreen.benchpress.login.http.LoginApi
import io.redgreen.benchpress.login.http.LoginRequest
import io.redgreen.benchpress.test.EffectHandlerTestCase
import org.junit.Test

class LoginEffectHandlerTest {
    private val viewAction = mock<LoginViewAction>()
    private val loginApi = mock<LoginApi>()
    private val effectHandler = LoginEffectHandler.createEffectHandler(viewAction, loginApi)
    private val testCase = EffectHandlerTestCase(effectHandler)

    @Test
    fun `it can dispatch goto home effect`() {
        // when
        testCase.dispatchEffect(GotoHome)

        // then
        testCase.assertNoOutgoingEvents()

        verify(viewAction).gotoHome()
        verifyNoMoreInteractions(viewAction)
    }

    @Test
    fun `it can dispatch login successful event if login succeeds`() {
        // given
        val email = "test@gmail.com"
        val password = "super-cool-password"

        val loginRequest = LoginRequest(email, password)
        whenever(loginApi.login(loginRequest))
            .thenReturn(Single.just(Unit))

        // when
        testCase.dispatchEffect(AttemptLogin(email, password))

        // then
        testCase.assertOutgoingEvents(LoginSucceeded)
    }

    @Test
    fun `it can dispatch login failed event if login fails`() {
        // given
        val email = "test@gmail.com"
        val password = "super-cool-password"

        val loginRequest = LoginRequest(email, password)

        whenever(loginApi.login(loginRequest))
            .thenReturn(Single.error(RuntimeException("Someone kicked the internet cable!")))

        // when
        testCase.dispatchEffect(AttemptLogin(email, password))

        // then
        testCase.assertOutgoingEvents(LoginFailed)
    }
}
