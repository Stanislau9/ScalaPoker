package card

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RankSpec extends AnyFlatSpec with Matchers {
  "Ace" should "more than King" in {
    Ace > King shouldEqual true
  }
  "Two" should "less than Three" in {
    Two > Three shouldEqual false
  }
  "List of Ranks" should "equal sorted list" in {

    List(Ace, Queen, Two, Three, King, Jack, Ten, Seven, Four, Six, Five, Nine, Eight).sorted.reverse
      .shouldEqual(List(Ace, King, Queen, Jack, Ten, Nine, Eight, Seven, Six, Five, Four, Three, Two))
  }
}
