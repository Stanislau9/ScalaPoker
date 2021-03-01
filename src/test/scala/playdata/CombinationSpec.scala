package playdata

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import Combination._

class CombinationSpec extends AnyFlatSpec with Matchers {
  "StraighFlush" should "more than FourKing" in {
    StraightFlush > FourKind shouldEqual true
  }
  "FullHouse" should "equal than FullHouse" in {
    FullHouse == FullHouse shouldEqual true
  }
  "Straigh" should "less than Flush" in {
    Straight > Flush shouldEqual false
  }
  "list Combinations" should "equal to list" in {
    List(Pair, Flush, FullHouse, FourKind, HighCard, StraightFlush, TwoPairs, Straight, ThreeKind).sorted
      .shouldEqual(List(HighCard, Pair, TwoPairs, ThreeKind, Straight, Flush, FullHouse, FourKind, StraightFlush))
  }

}
