package io.redgreen.benchpress.login.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EmailTest {
    @Test
    fun `empty emails are invalid`() {
        val emptyEmail = Email("")

        assertThat(emptyEmail.isValid())
            .isFalse()
    }

    @Test
    fun `good emails are valid`() {
        val goodEmail = Email("someone@somewhere.in")

        assertThat(goodEmail.isValid())
            .isTrue()
    }

    @Test
    fun `bad emails are invalid`() {
        val badEmail = Email("hello")

        assertThat(badEmail.isValid())
            .isFalse()
    }
}
