package com.github.shokohara

import scalaz._
import Scalaz._

object Case7 extends Case {

  def fizzBuzz(divisor: Int, name: String): Int => String \/ Int = (n: Int) => (n % divisor != 0) either n or name

  def toFizzBuzz(n: Int) = (fizzBuzz(3, "Fizz") |+| fizzBuzz(5, "Buzz")) (n)
    .fold(identity, ((_: Int) / 2) andThen (_.shows))
}
