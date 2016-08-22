package com.github.shokohara

object Main {

  def main(args: Array[String]): Unit = {
    Iterator.from(1)
      .map(toFizzBuzz)
      .take(100)
      .foreach(println)
  }

  def toFizzBuzz(n: Int) = (n % 3, n % 5) match {
    case (0, 0) => "FizzBuzz"
    case (_, 0) => "Buzz"
    case (0, _) => "Fizz"
    case (_, _) => n.toString
  }
}
