package playdata

import card.Card
import card.Rank._
import card.Suit._
import playdata.Hand._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TestCaseSpec extends AnyFlatSpec with Matchers {
  val card1: Card  = Card(Queen, Clubs)
  val card2: Card  = Card(Queen, Diamonds)
  val card3: Card  = Card(Queen, Spades)
  val card4: Card  = Card(Queen, Hearts)
  val card5: Card  = Card(Three, Clubs)
  val card6: Card  = Card(Three, Diamonds)
  val card7: Card  = Card(Three, Spades)
  val card8: Card  = Card(Three, Hearts)
  val card9: Card  = Card(Ace, Clubs)
  val card10: Card = Card(Ace, Diamonds)
  val card11: Card = Card(Ace, Spades)
  val card12: Card = Card(Ace, Hearts)
  val card13: Card = Card(Ten, Clubs)
  val card14: Card = Card(Ten, Diamonds)
  val card15: Card = Card(Ten, Spades)
  val card16: Card = Card(Ten, Hearts)

  val b: Board = Board(card1, card2, card3, card4, card5)

  val texH1: Hand = TexasHand(card6, card7, card8)
  val texH2: Hand = TexasHand(card9, card10, card11)
  val texH3: Hand = TexasHand(card12, card13, card1)
  val omaH1: Hand = OmahaHand(card6, card7, card8, card9)
  val omaH2: Hand = OmahaHand(card10, card11, card12, card13)
  val omaH3: Hand = OmahaHand(card14, card15, card16, card13)

  "List texas hands" should "create Right(TestCase)" in {
    TestCase(b, List(texH1, texH2)).create shouldEqual Right(TestCase(b, List(texH1, texH2)))
  }
  "List omaha hands" should "create Right(TestCase)" in {
    TestCase(b, List(omaH1, omaH2)).create shouldEqual Right(TestCase(b, List(omaH1, omaH2)))
  }
  "List texas hands with duplicate cards" should "create Left(Not valid cards)" in {
    TestCase(b, List(texH1, texH2, texH3)).create shouldEqual Left("Not valid cards")
  }
  "List omaha hands with duplicate cards" should "create Left(Not valid cards)" in {
    TestCase(b, List(omaH1, omaH2, omaH3)).create shouldEqual Left("Not valid cards")
  }
  "List different hands" should "create Left(Not valid hands)" in {
    TestCase(b, List(omaH1, texH2, omaH3)).create shouldEqual Left("Not valid hands")
  }
  "Empty List of hands" should "create Left(Not valid hands)" in {
    TestCase(b, List()).create shouldEqual Left("Not valid hands")
  }

}
