package com.github.shokohara

object Case9 {

  var >= : () => Any = null
  var <= = >=
  var ^ = <=.##
  var >> = ($ : Any) => if ($.==(^ = 1 + ^)) s"${""}" else s"${$}"
  var << = (## : Any) => if (##.## == (^ ^ ^)) ^ >>1 else ##
  var v: (() => Any) = {
    >= =() => >= =() => >= =() => { ;>= =v; "Fizz" } ;>=
  }
  var x: (() => Any) = {
    <= =() => <= =() => <= =() => <= =() => <= =() => { ;<= =x; "Buzz" } ;<=
  }
  Seq.fill("hello,world"('\n'))(println(<<(>>(>=())+ >>(<=()))))

}
