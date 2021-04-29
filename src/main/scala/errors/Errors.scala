package errors

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
case class NotABoard(string: String) extends Errors {
  override def toString: String = f"$string can't convert to board"
}
case class NotAHand(string: String) extends Errors {
  override def toString: String = f"$string can't convert to hand"
}
case object NotValidSet extends Errors {
  override def toString: String = "Invalid test set"
}
