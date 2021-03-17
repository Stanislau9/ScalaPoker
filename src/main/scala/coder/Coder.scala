package coder

import card._
import card.Suit._
import card.Rank._
import errors.Errors
import errors.Errors._

object Coder {

  trait Decoder[T] {
    def fromChar(char: Char): Either[Errors, T]
  }
  object Decoder {
    def apply[T: Decoder]: Decoder[T] = implicitly[Decoder[T]]
  }
  implicit class DecoderOps(char: Char) {
    def as[T: Decoder]: Either[Errors, T] = Decoder[T].fromChar(char)
  }

  implicit val charAsSuit: Decoder[Suit] = (char: Char) => {
    if (char == 'c') Right(Clubs)
    else if (char == 'd') Right(Diamonds)
    else if (char == 'h') Right(Hearts)
    else if (char == 's') Right(Spades)
    else Left(NotASuit(char))
  }

  implicit val charAsRank: Decoder[Rank] = (char: Char) => {
    if (char == '2') Right(Two)
    else if (char == '3') Right(Three)
    else if (char == '4') Right(Four)
    else if (char == '5') Right(Five)
    else if (char == '6') Right(Six)
    else if (char == '7') Right(Seven)
    else if (char == '8') Right(Eight)
    else if (char == '9') Right(Nine)
    else if (char == 'T') Right(Ten)
    else if (char == 'J') Right(Jack)
    else if (char == 'Q') Right(Queen)
    else if (char == 'K') Right(King)
    else if (char == 'A') Right(Ace)
    else Left(NotARank(char))
  }

  trait Encoder[T] {
    def toChar(t: T): Char
  }
  object Encoder {
    def apply[T: Encoder]: Encoder[T] = implicitly[Encoder[T]]
  }
  implicit class EncoderOps[T: Encoder](t: T) {
    def toChr: Char = Encoder[T].toChar(t)
  }

  implicit val suitToChar: Encoder[Suit] = {
    case Clubs    => 'c'
    case Diamonds => 'd'
    case Hearts   => 'h'
    case Spades   => 's'
  }

  implicit val rankToChar: Encoder[Rank] = {
    case Two   => '2'
    case Three => '3'
    case Four  => '4'
    case Five  => '5'
    case Six   => '6'
    case Seven => '7'
    case Eight => '8'
    case Nine  => '9'
    case Ten   => 'T'
    case Jack  => 'J'
    case Queen => 'Q'
    case King  => 'K'
    case Ace   => 'A'
  }

}
