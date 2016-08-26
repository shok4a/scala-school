package com.github.shokohara

import scalaz._
import Scalaz._

object Case8 {
  def fizzBuzz(divisor: Int, name: String) = ReaderT[String \/ ?, Int, Int] { (n: Int) =>
    (n % divisor != 0) either n or name
  }

  val toFizzBuzzEither: ReaderT[Lambda[β => Disjunction[String, β]], Int, Int] = for {
    _ <- fizzBuzz(15, "FizzBuzz")
    _ <- fizzBuzz(5, "Buzz")
    x <- fizzBuzz(3, "Fizz")
  } yield x

  val toFizzBuzz = toFizzBuzzEither.run andThen (_.fold(identity, _.shows))
}
