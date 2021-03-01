package playdata

import card.Card

trait Hand

case object Hand {
  final case class TexasHand(c1: Card, c2: Card, c3: Card)           extends Hand
  final case class OmahaHand(c1: Card, c2: Card, c3: Card, c4: Card) extends Hand
}
