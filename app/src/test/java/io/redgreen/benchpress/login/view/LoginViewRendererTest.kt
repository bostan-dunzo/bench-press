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

    @Test
    fun `it can render valid state`() {
        // given
        val validModel = blankModel
            .emailChanged("test@test.com")
            .passwordChanged("123456789")

        // when
        renderer.render(validModel)

        // then
        verify(view).enableSubmitButton()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render loading state`() {
        // given
        val attemptLoginModel = blankModel
            .emailChanged("test@test.com")
            .passwordChanged("123456789")
            .attemptLogin()

        // when
        renderer.render(attemptLoginModel)

        // then
        verify(view).disableEmailField()
        verify(view).disablePasswordField()
        verify(view).disableSubmitButton()
        verify(view).showLoading()
        verify(view).hideLoginFailedError()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render login success state`(){
        // given
        val loginSuccessModel = blankModel
            .emailChanged("test@test.com")
            .passwordChanged("123456789")
            .loginSuccessful()

        // when
        renderer.render(loginSuccessModel)

        verify(view).hideLoading()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `it can render login failed state`() {
        // given
        val loginFailedModel = blankModel
            .emailChanged("test@test.com")
            .passwordChanged("123456789")
            .loginFailed()

        //when
        renderer.render(loginFailedModel)

        verify(view).hideLoading()
        verify(view).showLoginFailedError()
        verify(view).enableEmail()
        verify(view).enablePassword()
        verify(view).enableSubmitButton()
    }

}
