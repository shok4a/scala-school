package com.github.shok4a

import com.github.shok4a.TBool._
import org.scalatest.{FlatSpec, Matchers}
import TInt._

class MainSpec extends FlatSpec with Matchers {

  "Main" should "finish to run main function without exception" in {
    noException should be thrownBy Main.main(Array.empty[String])
  }

  it should "works fine" in {
    ToBool[![TTrue]] === false
    ToBool[TTrue && TFalse] === false
    ToBool[TTrue || TFalse] === true
    ToBool[If[TTrue, TFalse, TTrue, TBool]] === true

    ToInt[T8 + T3] === 11
    ToInt[T8 + T_3] === 5
    ToInt[T_8 + T3] === -5
    ToInt[T_8 + T_3] === -11

    ToInt[T8 - T3] === 5
    ToInt[T8 - T_3] === 11
    ToInt[T_8 - T3] === -11
    ToInt[T_8 - T_3] === -5

    ToInt[T8 * T3] === 24
    ToInt[T8 * T_3] === -24
    ToInt[T_8 * T3] === -24
    ToInt[T_8 * T_3] === 24

    ToInt[T8 / T3] === 2
    ToInt[T8 / T_3] === -2
    ToInt[T_8 / T3] === -3
    ToInt[T_8 / T_3] === 3

    ToInt[T8 % T3] === 2
    ToInt[T8 % T_3] === 2
    ToInt[T_8 % T3] === 1
    ToInt[T_8 % T_3] === 1
  }
}
