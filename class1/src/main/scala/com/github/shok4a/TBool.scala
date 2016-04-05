package com.github.shok4a

import scala.language.higherKinds

sealed trait TBool {
  type Not <: TBool
  type And[A <: TBool] <: TBool
  type Or[A <: TBool] <: TBool
  type If[Then <: T, Else <: T, T] <: T
}

class TTrue extends TBool {
  type Not = TFalse
  type And[A <: TBool] = A
  type Or[A <: TBool] = TTrue
  type If[Then <: T, Else <: T, T] = Then
}

class TFalse extends TBool {
  type Not = TTrue
  type And[A <: TBool] = TFalse
  type Or[A <: TBool] = A
  type If[Then <: T, Else <: T, T] = Else
}

object TBool {
  type ![A <: TBool] = A#Not
  type &&[A <: TBool, B <: TBool] = A#And[B]
  type ||[A <: TBool, B <: TBool] = A#Or[B]
  type If[A <: TBool, Then <: T, Else <: T, T] = A#If[Then, Else, T]

  object ToBool {
    def apply[A <: TBool](implicit toBool: ToBool[A]): Boolean = toBool()
  }

  trait ToBool[A <: TBool] {
    def apply(): Boolean
  }

  implicit val toBoolTTrue = new ToBool[TTrue] {
    def apply() = true
  }
  implicit val toBoolTFalse = new ToBool[TFalse] {
    def apply() = false
  }
}
