import cats.effect.IO
import playdata.GameService

object Solver extends App {

  val y = for {
    service  <- GameService.of[IO]
    testcase <- service.create("texas-holdem 3d4s5dJsQd 5c4h 7sJd KcAs 9h7h 2dTc Qh8c TsJc")
  } yield testcase

  println(y.unsafeRunSync())

}
