package io.redgreen.benchpress.login.effecthandler

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.redgreen.benchpress.login.domain.LoginEffect.*
import io.redgreen.benchpress.test.EffectHandlerTestCase
import org.junit.Test

class LoginEffectHandlerTest {
    @Test
    fun `it can dispatch goto home effect`() {
        // given
        val viewAction = mock<LoginViewAction>()
        val effectHandler = LoginEffectHandler.createEffectHandler(viewAction)
        val testCase = EffectHandlerTestCase(effectHandler)

        // when
        testCase.dispatchEffect(GotoHome)

        // then
        testCase.assertNoOutgoingEvents()

        verify(viewAction).gotoHome()
        verifyNoMoreInteractions(viewAction)
    }
}