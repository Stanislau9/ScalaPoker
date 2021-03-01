package card

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RankSpec extends AnyFlatSpec with Matchers {
  "Ace" should "more than King" in {
    Rank.Ace > Rank.King shouldEqual true
  }
  "Two" should "less than Three" in {
    Rank.Two > Rank.Three shouldEqual false
  }
  "List of Ranks" should "equal sorted list" in {
    import Rank._
    List(Ace, Queen, Two, Three, King, Jack, Ten, Seven, Four, Six, Five, Nine, Eight).sorted.reverse
      .shouldEqual(List(Ace, King, Queen, Jack, Ten, Nine, Eight, Seven, Six, Five, Four, Three, Two))
  }
}
