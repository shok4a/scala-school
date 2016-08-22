package com.github.shokohara

import scalaz._
import Scalaz._
import Case2._

object Case4 {

  val toFizzBuzz: Int => String =
    (fizzBuzz(3, "Fizz") |+| fizzBuzz(5, "Buzz")) <*> { n: Int => fb: Option[String] => fb | n.shows }
}
