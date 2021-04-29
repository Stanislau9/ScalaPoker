package playdata

sealed trait GameType {}

case object Omaha    extends GameType
case object Texas    extends GameType
case object FiveCard extends GameType
