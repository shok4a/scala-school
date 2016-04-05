package com.github.shok4a

import com.github.okomok.sing.Dense._

object DenseEx {
  type _100 = _50#times[_2]
  val _100 = _50.times(_2)

  type _500 = _50#times[_10]
  val _500 = _50.times(_10)
}