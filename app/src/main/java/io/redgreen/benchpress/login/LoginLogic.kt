package io.redgreen.benchpress.login

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

object LoginLogic : Update<LoginModel, LoginEvent, LoginEffect> {
    override fun update(model: LoginModel, event: LoginEvent): Next<LoginModel, LoginEffect> {
        return if (event is LoginEvent.EmailChanged) {
            next(model.emailChanged(event.email))
        } else {
            TODO("Unsupported event $event")
        }
    }
}
