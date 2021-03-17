package playdata

import card._
import errors.Errors
import errors.Errors._
import playdata.Hand._
import coder.Coder.DecoderOps
import cats.implicits._

final case class TestCase(board: Board, hands: List[Hand]) {}

case object TestCase {

  def create(inputString: String): Either[Errors, TestCase] = {

    def strToCard(cardString: String): Either[Errors, Card] = {
      if (cardString.length == 2)
        for {
          rank <- cardString(0).as[Rank]
          suit <- cardString(1).as[Suit]
        } yield Card(rank, suit)
      else
        Left(NotACard(cardString))
    }

    def strToHand(handString: String): Either[Errors, Hand] = {

      val cards = for {
        strCard <- handString.split("(?<=\\G.{2})").toList
        card = strToCard(strCard)
      } yield card

      cards match {
        case Right(c1) :: Right(c2) :: Right(c3) :: Right(c4) :: Nil => Right(OmahaHand(c1, c2, c3, c4))
        case Right(c1) :: Right(c2) :: Nil                           => Right(TexasHand(c1, c2))
        case _                                                       => Left(NotAHand(handString))
      }
    }

    def strToBoard(boardString: String): Either[Errors, Board] = {

      val cards = for {
        strCard <- boardString.split("(?<=\\G.{2})").toList
        card = strToCard(strCard)
      } yield card

      cards match {
        case Right(c1) :: Right(c2) :: Right(c3) :: Right(c4) :: Right(c5) :: Nil => Right(Board(c1, c2, c3, c4, c5))
        case _                                                                    => Left(NotABoard(boardString))
      }

    }

    val stringSet: List[String]                = inputString.trim.split(" +").toList
    val inputBoard: Either[Errors, Board]      = strToBoard(stringSet.headOption.getOrElse(""))
    val inputHands: Either[Errors, List[Hand]] = stringSet.tail.map(strToHand).sequence

    for {
      b <- inputBoard
      h <- inputHands
      cards = (b match {
        case Board(c1, c2, c3, c4, c5) => List(c1, c2, c3, c4, c5)
      }) ++
        h.flatMap {
          case TexasHand(c1, c2)         => List(c1, c2)
          case OmahaHand(c1, c2, c3, c4) => List(c1, c2, c3, c4)
          case _                         => Nil
        }
      _ <- Either.cond(cards == cards.distinct, (), NotValidSet)
      _ <- Either.cond(h.map(x => x.isInstanceOf[OmahaHand]).distinct.length == 1, (), NotValidSet)

    } yield TestCase(b, h)

  }
}
