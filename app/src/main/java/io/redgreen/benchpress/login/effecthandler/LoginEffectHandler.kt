package io.redgreen.benchpress.login.effecthandler

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.login.domain.LoginEffect
import io.redgreen.benchpress.login.domain.LoginEffect.AttemptLogin
import io.redgreen.benchpress.login.domain.LoginEffect.GotoHome
import io.redgreen.benchpress.login.domain.LoginEvent
import io.redgreen.benchpress.login.domain.LoginEvent.LoginFailed
import io.redgreen.benchpress.login.domain.LoginEvent.LoginSucceeded
import io.redgreen.benchpress.login.http.LoginApi
import io.redgreen.benchpress.login.http.LoginRequest

object LoginEffectHandler {
    fun createEffectHandler(
        viewAction: LoginViewAction,
        loginApi: LoginApi
    ): ObservableTransformer<LoginEffect, LoginEvent> {
        return RxMobius
            .subtypeEffectHandler<LoginEffect, LoginEvent>()
            .addAction(GotoHome::class.java) { viewAction.gotoHome() }
            .addTransformer(AttemptLogin::class.java) { attemptLogins ->
                attemptLogins
                    .map { (email, password) -> LoginRequest(email, password) }
                    .switchMap { makeLoginCall(loginApi, it) }
            }
            .build()
    }

    private fun makeLoginCall(
        loginApi: LoginApi,
        request: LoginRequest
    ): Observable<LoginEvent> {
        return loginApi
            .login(request)
            .toObservable()
            .map { LoginSucceeded as LoginEvent }
            .onErrorReturn { LoginFailed }
    }
}
