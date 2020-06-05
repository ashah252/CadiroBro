package com.equinox.cadiro

import com.equinox.cadiro.utils.CadiroLogManager
import org.junit.Test
import org.junit.Assert.assertTrue

class SampleTest {

  @Test
  def sampleTestSuite(): Unit = {
    CadiroLogManager.logger.info("Running SampleTest.sampleTestSuit")
    CadiroLogManager.logger.debug("Assert true is True")
    assertTrue(true)
  }

}
