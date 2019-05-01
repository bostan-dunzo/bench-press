package io.redgreen.benchpress

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CanaryInstrumentedTest {
  @Test
  fun testEnvironmentIsSetup() {
    val appContext = InstrumentationRegistry.getTargetContext()
  }
}
