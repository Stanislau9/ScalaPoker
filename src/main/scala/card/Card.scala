package card

final case class Card(rank: Rank, suit: Suit) extends Ordered[Card] {
  override def compare(that: Card): Int = this.rank compare that.rank
}
