package com.github.shokohara

import scalaz._
import Scalaz._

object Case6 {

  def fizzBuzz(divisor: Int, name: String) =
    ReaderT[Option, Int, String] { (n: Int) => n % divisor == 0 option name }

  val toFizzBuzzOption: Kleisli[Option, Int, String] = fizzBuzz(3, "Fizz") |+| fizzBuzz(5, "Buzz")

  val toFizzBuzz = (n: Int) => toFizzBuzzOption.run(n) | n.shows
}
