package com.github.shok4a

import com.github.okomok.sing.Dense._
import com.github.shok4a.DenseEx._
import org.scalatest.{FlatSpec, Matchers}

class MainSpec extends FlatSpec with Matchers {

  "Main" should "finish to run main function without exception" in {
    noException should be thrownBy Main.main(Array.empty[String])
  }

  "BeforeCoin" should "throw exception" in {
    an[AssertionError] should be thrownBy BeforeCoin(5, 3) + BeforeCoin(10, 6)
  }

  "Coin" should "works fine" in {
    Coin(_1, 1).sum === 1
    Coin(_5, 2).sum === 10
    Coin(_10, 3).sum === 30
    Coin(_50, 4).sum === 200
    Coin(_100, 5).sum === 500
    Coin(_500, 6).sum === 3000
    Coin(_1, 1) shouldBe a[Coin[_1]]
    Coin(_5, 2) shouldBe a[Coin[_5]]
    Coin(_10, 3) shouldBe a[Coin[_10]]
    Coin(_50, 4) shouldBe a[Coin[_50]]
    Coin(_100, 5) shouldBe a[Coin[_100]]
    Coin(_500, 6) shouldBe a[Coin[_500]]
  }
}
