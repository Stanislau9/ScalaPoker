package playdata

sealed trait Combination extends Ordered[Combination] {
  def weight: Int
  override def compare(that: Combination): Int = this.weight compare that.weight
}
case object Combination {
  final case object StraightFlush extends Combination {
    override def weight: Int = 9
  }
  final case object FourKind extends Combination {
    override def weight: Int = 8
  }
  final case object FullHouse extends Combination {
    override def weight: Int = 7
  }
  final case object Flush extends Combination {
    override def weight: Int = 6
  }
  final case object Straight extends Combination {
    override def weight: Int = 5
  }
  final case object ThreeKind extends Combination {
    override def weight: Int = 4
  }
  final case object TwoPairs extends Combination {
    override def weight: Int = 3
  }
  final case object Pair extends Combination {
    override def weight: Int = 2
  }
  final case object HighCard extends Combination {
    override def weight: Int = 1
  }
}
