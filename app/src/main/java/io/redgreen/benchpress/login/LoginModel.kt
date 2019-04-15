package io.redgreen.benchpress.login

import android.os.Parcelable
import io.redgreen.benchpress.architecture.AsyncOp
import io.redgreen.benchpress.architecture.AsyncOp.FAILED
import io.redgreen.benchpress.architecture.AsyncOp.IDLE
import io.redgreen.benchpress.architecture.AsyncOp.IN_FLIGHT
import io.redgreen.benchpress.architecture.AsyncOp.SUCCEEDED
import io.redgreen.benchpress.login.domain.Email
import io.redgreen.benchpress.login.domain.Password
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginModel(
  val email: Email,
  val password: Password,
  val attemptLoginAsyncOp: AsyncOp
) : Parcelable {
  companion object {
    val BLANK = LoginModel(
      email = Email(""),
      password = Password(""),
      attemptLoginAsyncOp = IDLE
    )
  }

  val isReadyForLogin: Boolean
    get() = email.isValid() && password.isValid()

  fun emailChanged(value: String): LoginModel =
    copy(email = Email(value))

  fun passwordChanged(value: String): LoginModel =
    copy(password = Password(value))

  fun loginAttempted(): LoginModel =
    copy(attemptLoginAsyncOp = IN_FLIGHT)

  fun authenticationSucceeded(): LoginModel =
    copy(attemptLoginAsyncOp = SUCCEEDED)

  fun authenticationFailed(): LoginModel =
    copy(attemptLoginAsyncOp = FAILED)

  /* Normally, we wouldn't override this function in a data class. We are doing this because we want to print the 'isReadyForLogin' computed property. */
  override fun toString(): String =
    "LoginModel(email=$email, " +
        "password=$password, " +
        "attemptLoginAsyncOp=$attemptLoginAsyncOp, " +
        "isReadyForLogin=$isReadyForLogin)"
}
