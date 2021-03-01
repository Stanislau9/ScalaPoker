package card

sealed trait Rank extends Ordered[Rank] {
  def weight: Int
  override def compare(that: Rank): Int = this.weight compare that.weight
}

case object Rank /*extends AnyVal*/ {
  final case object Two extends Rank {
    override def weight: Int = 2
  }
  final case object Three extends Rank {
    override def weight: Int = 3
  }
  final case object Four extends Rank {
    override def weight: Int = 4
  }
  final case object Five extends Rank {
    override def weight: Int = 5
  }
  final case object Six extends Rank {
    override def weight: Int = 6
  }
  final case object Seven extends Rank {
    override def weight: Int = 7
  }
  final case object Eight extends Rank {
    override def weight: Int = 8
  }
  final case object Nine extends Rank {
    override def weight: Int = 9
  }
  final case object Ten extends Rank {
    override def weight: Int = 10
  }
  final case object Jack extends Rank {
    override def weight: Int = 11
  }
  final case object Queen extends Rank {
    override def weight: Int = 12
  }
  final case object King extends Rank {
    override def weight: Int = 13
  }
  final case object Ace extends Rank {
    override def weight: Int = 14
  }
}
