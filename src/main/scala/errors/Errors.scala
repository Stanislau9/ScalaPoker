package errors

sealed trait Errors

case object Errors {
  final case class NotARank(char: Char) extends Errors {
    override def toString: String = f"$char can't convert to rank"
  }
  final case class NotASuit(char: Char) extends Errors {
    override def toString: String = f"$char can't convert to suit"
  }
  final case class NotABoard(string: String) extends Errors {
    override def toString: String = f"$string can't convert to board"
  }
  final case class NotAHand(string: String) extends Errors {
    override def toString: String = f"$string can't convert to hand"
  }
  final case object NotValidSet extends Errors {
    override def toString: String = "Invalid test set"
  }
}
