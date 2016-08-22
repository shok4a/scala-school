package com.github.shokohara

import scalaz._
import Scalaz._

object Case1 extends Case {

  def toFizzBuzz(n: Int) = {
    def fizzBuzz(divisor: Int, name: String) = (n % divisor === 0) option name
    fizzBuzz(3, "Fizz") |+| fizzBuzz(5, "Buzz") | n.shows
  }
}
