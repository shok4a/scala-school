package com.github.shokohara

import scalaz._
import Scalaz._

object Case2 extends Case {

  def fizzBuzz(divisor: Int, name: String): Int => Option[String] = (n: Int) => n % divisor === 0 option name

  def toFizzBuzz(n: Int): String = (fizzBuzz(3, "Fizz") |+| fizzBuzz(5, "Buzz"))(n) | n.shows
}
