package playdata

import card.Card

sealed trait Hand

case class TexasHand(c1: Card, c2: Card)                                  extends Hand
case class OmahaHand(c1: Card, c2: Card, c3: Card, c4: Card)              extends Hand
case class FiveCardHand(c1: Card, c2: Card, c3: Card, c4: Card, c5: Card) extends Hand
