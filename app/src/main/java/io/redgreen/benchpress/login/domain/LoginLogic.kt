package io.redgreen.benchpress.login.domain

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update
import io.redgreen.benchpress.login.domain.LoginEffect.AttemptLogin
import io.redgreen.benchpress.login.domain.LoginEffect.GotoHome
import io.redgreen.benchpress.login.domain.LoginEvent.*

object LoginLogic : Update<LoginModel, LoginEvent, LoginEffect> {
    override fun update(model: LoginModel, event: LoginEvent): Next<LoginModel, LoginEffect> {
        return when (event) {
            is EmailChanged -> next(model.emailChanged(event.email))
            is PasswordChanged -> next(model.passwordChanged(event.password))
            is LoginAttempted -> next(model.attemptLogin(), setOf(AttemptLogin(model.email.value, model.password.value)))
            is LoginSucceeded -> next(model.loginSuccessful(), setOf(GotoHome))
            is LoginFailed -> next(model.loginFailed())
            else -> TODO("Unsupported event: $event")
        }
    }
}
