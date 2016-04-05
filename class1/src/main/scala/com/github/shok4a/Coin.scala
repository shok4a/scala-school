package com.github.shok4a

import com.github.okomok.sing.Dense
import com.github.okomok.sing.Dense._
import com.github.shok4a.DenseEx._

sealed trait CoinValue[T <: Dense]
object CoinValue {
  implicit object i1 extends CoinValue[_1]
  implicit object i5 extends CoinValue[_5]
  implicit object i10 extends CoinValue[_10]
  implicit object i50 extends CoinValue[_50]
  implicit object i100 extends CoinValue[_100]
  implicit object i500 extends CoinValue[_500]
}

case class Coin[Value <: Dense](value: Value, num: Int)(implicit i: CoinValue[Value]) {
  def +(a: Coin[Value]) = Coin(value, num + a.num)
  def -(a: Coin[Value]) = Coin(value, num - a.num)
  def sum = value.unsing * num
}
