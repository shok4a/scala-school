package com.github.shokohara

import scalaz._
import Scalaz._

object Case5 {
  //  def fizzBuzz(divisor: Int, name: String) = ReaderT[Option, Int, String] { (n: Int) =>
  //    (n % divisor == 0) option name
  //  }
  //
  //  val toFizzBuzzOption =
  //    fizzBuzz(15, "FizzBuzz") <+> fizzBuzz(5, "Buzz") <+> fizzBuzz(3, "Fizz")
  //
  //  val toFizzBuzz = { (n: Int) =>
  //    toFizzBuzzOption.run(n) | n.shows
  //  }
}
