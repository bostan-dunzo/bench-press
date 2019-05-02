package io.redgreen.benchpress.login

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update
import io.redgreen.benchpress.login.LoginEvent.EmailChanged

object LoginLogic : Update<LoginModel, LoginEvent, LoginEffect> {
    override fun update(model: LoginModel, event: LoginEvent): Next<LoginModel, LoginEffect> {
        return when (event) {
            is EmailChanged -> next(model.emailChanged(event.email))
            is LoginEvent.PasswordChanged -> next(model.passwordChanged(event.password))
            else -> TODO("Unsupported event: $event")
        }
    }
}
