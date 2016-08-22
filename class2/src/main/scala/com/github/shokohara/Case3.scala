package com.github.shokohara

import scalaz._
import Scalaz._
import Case2._

object Case3 {

  val toFizzBuzz: Int => String =
    ((fizzBuzz(3, "Fizz") |+| fizzBuzz(5, "Buzz")) &&& identity) >>> ((_: Option[String]) | (_: Int).shows).tupled
}
