package io.redgreen.benchpress.login.effecthandler

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.login.domain.LoginEffect
import io.redgreen.benchpress.login.domain.LoginEffect.GotoHome
import io.redgreen.benchpress.login.domain.LoginEvent

object LoginEffectHandler {
    fun createEffectHandler(
        viewAction: LoginViewAction
    ): ObservableTransformer<LoginEffect, LoginEvent> {
        return RxMobius
            .subtypeEffectHandler<LoginEffect, LoginEvent>()
            .addAction(GotoHome::class.java) { viewAction.gotoHome() }
            .build()
    }
}
