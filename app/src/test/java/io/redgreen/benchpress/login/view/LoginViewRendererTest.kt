package io.redgreen.benchpress.login.view

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.redgreen.benchpress.login.domain.LoginModel
import org.junit.Test

class LoginViewRendererTest {
    private val view = mock<LoginView>()
    private val renderer = LoginViewRenderer(view)
    private val blankModel = LoginModel.BLANK

    @Test
    fun `it can render blank state`() {
        // when
        renderer.render(blankModel)

        // then
        verify(view).disableSubmitButton()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render invalid state`() {
        // given
        val invalidModel = blankModel
            .emailChanged("invalid-email")
            .passwordChanged("**")

        // when
        renderer.render(invalidModel)

        // then
        verify(view).disableSubmitButton()
        verifyNoMoreInteractions(view)
    }
}
