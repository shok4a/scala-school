package com.github.shok4a

case class BeforeCoin(value: Int, num: Int) {
  def +(a: BeforeCoin) = {
    assert(value == a.value, "違う種類の硬貨を足しています")
    BeforeCoin(value, num + a.num)
  }

  def -(a: BeforeCoin) = {
    assert(value == a.value, "違う種類の硬貨を引いています")
    BeforeCoin(value, num - a.num)
  }

  def sum = value * num
}