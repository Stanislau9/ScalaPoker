package card

sealed trait Rank extends Ordered[Rank] {
  def weight: Int
  override def compare(that: Rank): Int = this.weight compare that.weight
}

case object Two extends Rank {
  override def weight: Int = 2
}
case object Three extends Rank {
  override def weight: Int = 3
}
case object Four extends Rank {
  override def weight: Int = 4
}
case object Five extends Rank {
  override def weight: Int = 5
}
case object Six extends Rank {
  override def weight: Int = 6
}
case object Seven extends Rank {
  override def weight: Int = 7
}
case object Eight extends Rank {
  override def weight: Int = 8
}
case object Nine extends Rank {
  override def weight: Int = 9
}
case object Ten extends Rank {
  override def weight: Int = 10
}
case object Jack extends Rank {
  override def weight: Int = 11
}
case object Queen extends Rank {
  override def weight: Int = 12
}
case object King extends Rank {
  override def weight: Int = 13
}
case object Ace extends Rank {
  override def weight: Int = 14
}
