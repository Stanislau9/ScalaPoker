package playdata
import card.Card
import playdata.Hand._

final case class TestCase(board: Board, hands: List[Hand]) {
  def create: Either[String, TestCase] = {
    val cards: List[Card] = {
      (board match {
        case Board(c1, c2, c3, c4, c5) => List(c1, c2, c3, c4, c5)
        case _                         => Nil
      }) ++ hands.flatMap {
        case TexasHand(c1, c2, c3)     => List(c1, c2, c3)
        case OmahaHand(c1, c2, c3, c4) => List(c1, c2, c3, c4)
        case _                         => Nil
      }
    }
    if (hands.map(x => x.isInstanceOf[OmahaHand]).distinct.length != 1) Left("Not valid hands")
    else if (cards != cards.distinct) Left("Not valid cards")
    else Right(TestCase(board, hands))
  }
}
