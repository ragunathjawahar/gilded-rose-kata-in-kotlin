package sellin

class NoopSellInUpdater : SellInUpdater {
  override fun update(sellIn: Int): Int =
      sellIn
}
