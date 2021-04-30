package errors

import card.Card

sealed trait Errors

case class NotARank(char: Char) extends Errors {
  override def toString: String = f"$char can't convert to rank"
}
case class NotASuit(char: Char) extends Errors {
  override def toString: String = f"$char can't convert to suit"
}
case class NotACard(string: String) extends Errors {
  override def toString: String = f"$string can't convert to card"
}
case class NotABoard(cards: List[Card]) extends Errors {
  override def toString: String = f"$cards can't convert to board"
}
case class NotAHand(cards: List[Card]) extends Errors {
  override def toString: String = f"$cards can't convert to hand"
}
case object NotValidSet extends Errors {
  override def toString: String = "Invalid test set"
}

case class NotSupportGameType(string: String) extends Errors {
  override def toString: String = f"$string don't support"
}
