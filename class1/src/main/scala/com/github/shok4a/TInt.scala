package com.github.shok4a

import scala.language.higherKinds
import TBool._

sealed trait TInt {
  type Neg <: TInt
  type Abs <: TInt
  type Suc <: TInt
  type Pre <: TInt
  type Add[A <: TInt] <: TInt
  type Sub[A <: TInt] = Add[A#Neg]
  type Mul[A <: TInt] <: TInt
  // type Div[A <: TInt] <: TInt
  protected type TSucDivTSuc[A <: TInt] <: TInt
  type Div[A <: TInt] = IfTZeroTSucTPre[
    TZero,
    A#IfTZeroTSucTPre[TZero, TSucDivTSuc[A], TSucDivTSuc[A#Neg]#Neg, TInt],
    A#IfTZeroTSucTPre[TZero, Neg#TSucDivTSuc[A]#Suc#Neg, Neg#TSucDivTSuc[A#Neg]#Suc, TInt],
    TInt]
  type Mod[A <: TInt] <: TInt

  type IfTZeroTSucTPre[TZeroThen <: T, TSucThen <: T, TPreThen <: T, T] <: T
  type IsTZero = IfTZeroTSucTPre[TTrue, TFalse, TFalse, TBool]
  type IsTSuc = IfTZeroTSucTPre[TFalse, TTrue, TFalse, TBool]
  type IsTPre = IfTZeroTSucTPre[TFalse, TFalse, TTrue, TBool]

  type Equal[A <: TInt] = Sub[A]#IsTZero
  type Greater[A <: TInt] = Sub[A]#IsTSuc
  type Less[A <: TInt] = Sub[A]#IsTPre
}

trait TPos extends TInt
trait TNeg extends TInt

class TZero extends TPos with TNeg {
  type Neg = TZero
  type Abs = TZero
  type Suc = TSuc[TZero]
  type Pre = TPre[TZero]
  type Add[A <: TInt] = A
  type Mul[A <: TInt] = TZero
  // type Div[A <: TInt] = TZero
  type TSucDivTSuc[A <: TInt] = TZero
  type Mod[A <: TInt] = TZero

  type IfTZeroTSucTPre[TZeroThen <: T, TSucThen <: T, TPreThen <: T, T] = TZeroThen
}

class TSuc[P <: TPos] extends TPos {
  type Neg = Pre#Neg#Pre
  type Abs = Pre#Suc
  type Suc = TSuc[TSuc[Pre]]
  type Pre = P
  type Add[A <: TInt] = Pre#Add[A]#Suc
  type Mul[A <: TInt] = Pre#Mul[A]#Add[A]
  /*
  type Div[A <: TInt] = A#IfTZeroTSucTPre[
    TZero, // ゼロで割った商はゼロとする
    If[Less[A], TZero, Sub[A]#Div[A]#Suc, TInt],
    If[Less[A#Neg], TZero, Add[A]#Div[A]#Pre, TInt],
    TInt]
  */
  type TSucDivTSuc[A <: TInt] = If[Less[A#Abs], TZero, Sub[A#Abs]#TSucDivTSuc[A]#Suc, TInt]
  type Mod[A <: TInt] = A#IfTZeroTSucTPre[
    TZero, // ゼロで割った余りはゼロとする
    If[Less[A], Pre#Suc, Sub[A]#Mod[A], TInt],
    If[Less[A#Neg], Pre#Suc, Add[A]#Mod[A], TInt],
    TInt]

  type IfTZeroTSucTPre[TZeroThen <: T, TSucThen <: T, TPreThen <: T, T] = TSucThen
}

class TPre[S <: TNeg] extends TNeg {
  type Neg = Suc#Neg#Suc
  type Abs = Neg
  type Suc = S
  type Pre = TPre[TPre[Suc]]
  type Add[A <: TInt] = Suc#Add[A]#Pre
  type Mul[A <: TInt] = Suc#Mul[A]#Sub[A]
  /*
  type Div[A <: TInt] = A#IfTZeroTSucTPre[
    TZero, // ゼロで割った商はゼロとする
    Add[A]#Div[A]#Pre,
    Sub[A]#Div[A]#Suc,
    TInt]
  */
  type TSucDivTSuc[A <: TInt] = TZero
  type Mod[A <: TInt] = A#IfTZeroTSucTPre[
    TZero, // ゼロで割った余りはゼロとする
    Add[A]#Mod[A],
    Sub[A]#Mod[A],
    TInt]

  type IfTZeroTSucTPre[TZeroThen <: T, TSucThen <: T, TPreThen <: T, T] = TPreThen
}

object TInt {
  type +[N1 <: TInt, N2 <: TInt] = N1#Add[N2]
  type -[N1 <: TInt, N2 <: TInt] = N1#Sub[N2]
  type *[N1 <: TInt, N2 <: TInt] = N1#Mul[N2]
  type /[N1 <: TInt, N2 <: TInt] = N1#Div[N2]
  type %[N1 <: TInt, N2 <: TInt] = N1#Mod[N2]

  type ==[A <: TInt, B <: TInt] = A#Equal[B]
  type !=[A <: TInt, B <: TInt] = A#Equal[B]#Not
  type  >[A <: TInt, B <: TInt] = A#Greater[B]
  type <=[A <: TInt, B <: TInt] = A#Greater[B]#Not
  type  <[A <: TInt, B <: TInt] = A#Less[B]
  type >=[A <: TInt, B <: TInt] = A#Less[B]#Not

  object ToInt {
    def apply[N <: TInt](implicit toIntN: ToInt[N]): Int = toIntN()
  }
  trait ToInt[N <: TInt] {
    def apply(): Int
  }
  implicit def toIntTZero = new ToInt[TZero] {
    def apply() = 0
  }
  implicit def toIntTSuc[P <: TPos](implicit toIntP: ToInt[P]) = new ToInt[TSuc[P]] {
    def apply() = toIntP() + 1
  }
  implicit def toIntTPre[S <: TNeg](implicit toIntS: ToInt[S]) = new ToInt[TPre[S]] {
    def apply() = toIntS() - 1
  }

  type T0 = TZero
  type T1 = T0#Suc
  type T2 = T1#Suc
  type T3 = T2#Suc
  type T4 = T3#Suc
  type T5 = T4#Suc
  type T6 = T5#Suc
  type T7 = T6#Suc
  type T8 = T7#Suc
  type T9 = T8#Suc

  type T_1 = T0#Pre
  type T_2 = T_1#Pre
  type T_3 = T_2#Pre
  type T_4 = T_3#Pre
  type T_5 = T_4#Pre
  type T_6 = T_5#Pre
  type T_7 = T_6#Pre
  type T_8 = T_7#Pre
  type T_9 = T_8#Pre
}
