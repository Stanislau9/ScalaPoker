package playdata

import card.{Card, Rank, Suit}
import cats._
import cats.implicits._
import errors.{Errors, NotABoard, NotACard, NotAHand, NotSupportGameType, NotValidSet}
import coder.Coder.DecoderOps

trait GameService[F[_]] {
  def create(inputString: String): F[Either[Errors, TestCase]]
  def solve(testCase: TestCase): F[Result]
}

object GameService {
  def of[F[_]: Applicative]: F[GameService[F]] =
    new GameService[F] {
      override def create(inputString: String): F[Either[Errors, TestCase]] = {
        val stringSet: List[String] = inputString.trim.split(" +").toList
        if (stringSet.length < 3) Left(NotValidSet)
        else
          for {
            gameType <- strToGameType(stringSet.head)
            board    <- strToBoard(stringSet(1))
            hands    <- ListToHands(stringSet.drop(2), gameType)
            _        <- repeatCheck(board, hands)
          } yield TestCase(gameType, board, hands)
      }.pure[F]

      override def solve(testCase: TestCase): F[Result] = ???

      private def strToCards(cardsString: String): Either[Errors, List[Card]] = {
        cardsString
          .split("(?<=\\G.{2})")
          .toList
          .traverse { str =>
            if (str.length != 2) Left(NotACard(str))
            else
              for {
                rank <- str(0).as[Rank]
                suit <- str(1).as[Suit]
              } yield Card(rank, suit)
          }
      }

      private def cardsToHand(c: List[Card], gameType: GameType): Either[Errors, Hand] = {
        if (c.length == 2 && gameType == Texas) Right(TexasHand(c.head, c(1)))
        else if (c.length == 4 && gameType == Omaha) Right(OmahaHand(c.head, c(1), c(2), c(3)))
        else if (c.length == 5 && gameType == FiveCard) Right(FiveCardHand(c.head, c(1), c(2), c(3), c(4)))
        else Left(NotAHand(c))
      }

      private def cardsToBoard(c: List[Card]): Either[Errors, Board] =
        if (c.length == 5) Right(Board(c.head, c(1), c(2), c(3), c(4)))
        else Left(NotABoard(c))

      private def strToHands(handString: String, gameType: GameType): Either[Errors, Hand] =
        strToCards(handString).flatMap(cardsList => cardsToHand(cardsList, gameType))

      private def ListToHands(handsList: List[String], gameType: GameType): Either[Errors, List[Hand]] =
        handsList.traverse(handStr => strToHands(handStr, gameType))

      private def strToBoard(boardString: String): Either[Errors, Board] =
        strToCards(boardString).flatMap(cardsToBoard)

      private def strToGameType(gameString: String): Either[Errors, GameType] =
        gameString match {
          case "texas-holdem"   => Right(Texas)
          case "omaha-holdem"   => Right(Omaha)
          case "five-card-draw" => Right(FiveCard)
          case _                => Left(NotSupportGameType(gameString))
        }

      private def repeatCheck(board: Board, hands: List[Hand]): Either[Errors, Unit] = {
        val cards: List[Card] = (board match {
          case Board(c1, c2, c3, c4, c5) => List(c1, c2, c3, c4, c5)
        }) ++
          hands.flatMap {
            case TexasHand(c1, c2)                => List(c1, c2)
            case OmahaHand(c1, c2, c3, c4)        => List(c1, c2, c3, c4)
            case FiveCardHand(c1, c2, c3, c4, c5) => List(c1, c2, c3, c4, c5)
          }
        if (cards.distinct.length != cards.length) Left(NotValidSet)
        else Right(())
      }

    }.pure[F]
}
