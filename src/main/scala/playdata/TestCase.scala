package playdata

import card._
import coder.Coder.DecoderOps
import cats.implicits._
import errors._

sealed abstract case class TestCase private (board: Board, hands: List[Hand]) {}

case object TestCase {

  private def strToCard(cardString: String): Either[Errors, Card] = {

    if (cardString.length == 2)
      for {
        rank <- cardString(0).as[Rank]
        suit <- cardString(1).as[Suit]
      } yield Card(rank, suit)
    else
      Left(NotACard(cardString))

  }

  private def strToHand(handString: String): Either[Errors, Hand] = {

    handString
      .split("(?<=\\G.{2})")
      .toList
      .traverse(strToCard)
      .flatMap {
        case c if c.length == 4 => Right(OmahaHand(c.head, c(1), c(2), c(3)))
        case c if c.length == 2 => Right(TexasHand(c.head, c(1)))
        case _                  => Left(NotAHand(handString))
      }

  }

  private def strToBoard(boardString: String): Either[Errors, Board] = {

    boardString
      .split("(?<=\\G.{2})")
      .toList
      .traverse(strToCard)
      .flatMap {
        case c if c.length == 5 => Right(Board(c.head, c(1), c(2), c(3), c(4)))
        case _                  => Left(NotABoard(boardString))
      }

  }

  def create(inputString: String): Either[Errors, TestCase] = {

    val stringSet: List[String]                = inputString.trim.split(" +").toList
    val inputBoard: Either[Errors, Board]      = strToBoard(stringSet.headOption.getOrElse(""))
    val inputHands: Either[Errors, List[Hand]] = stringSet.tail.traverse(strToHand)

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
      y = h.collect { case hand: OmahaHand => hand }.distinct

    } yield {
      println(y)
      new TestCase(b, h) {}
    }

  }
}
