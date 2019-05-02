package io.redgreen.benchpress.login

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update
import io.redgreen.benchpress.login.LoginEffect.AttemptLogin
import io.redgreen.benchpress.login.LoginEvent.*

object LoginLogic : Update<LoginModel, LoginEvent, LoginEffect> {
    override fun update(model: LoginModel, event: LoginEvent): Next<LoginModel, LoginEffect> {
        return when (event) {
            is EmailChanged -> next(model.emailChanged(event.email))
            is PasswordChanged -> next(model.passwordChanged(event.password))
            is LoginAttempted -> next(model.attemptLogin(), setOf(AttemptLogin(model.email.value, model.password.value)))
            else -> TODO("Unsupported event: $event")
        }
    }
}
