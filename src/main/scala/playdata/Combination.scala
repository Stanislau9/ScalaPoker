package playdata

sealed trait Combination extends Ordered[Combination] {
  def weight: Int
  override def compare(that: Combination): Int = this.weight compare that.weight
}

case object StraightFlush extends Combination {
  override def weight: Int = 9
}
case object FourKind extends Combination {
  override def weight: Int = 8
}
case object FullHouse extends Combination {
  override def weight: Int = 7
}
case object Flush extends Combination {
  override def weight: Int = 6
}
case object Straight extends Combination {
  override def weight: Int = 5
}
case object ThreeKind extends Combination {
  override def weight: Int = 4
}
case object TwoPairs extends Combination {
  override def weight: Int = 3
}
case object Pair extends Combination {
  override def weight: Int = 2
}
case object HighCard extends Combination {
  override def weight: Int = 1
}
