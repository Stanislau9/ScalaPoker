import errors.Errors
import playdata.TestCase

object Solver extends App {

  val a: Either[Errors, TestCase] =
    TestCase.create("5c6dAcAsQs TsQh9hQc 8d7cTcJd 5s5d7s4d Qd3cKs4c KdJs2hAh Kh4hKc7h 6h7d2cJc")
  println(a)

}
