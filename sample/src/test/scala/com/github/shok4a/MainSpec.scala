package com.github.shok4a

import org.scalatest.{FlatSpec, Matchers}

class MainSpec extends FlatSpec with Matchers {

  "Main" should "finish to run main function without exception" in {
    noException should be thrownBy Main.main(Array.empty[String])
  }
}
