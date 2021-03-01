package card

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import Rank._
import Suit._
class CardSpec extends AnyFlatSpec with Matchers {
  "Ah" should "more than 2c" in {
    Card(Ace, Hearts) > Card(Two, Clubs) shouldEqual true
  }
  "Td" should "more than 7s" in {
    Card(Ten, Diamonds) > Card(Seven, Spades) shouldEqual true
  }
  "6h" should "less than Qh" in {
    Card(Six, Hearts) > Card(Queen, Hearts) shouldEqual false
  }
  "Kd" should "equal Kd" in {
    Card(King, Diamonds) == Card(King, Diamonds) shouldEqual true
  }
}
