interface SellInUpdater {
  fun update(sellIn: Int): Int
}

class NoopSellInUpdater : SellInUpdater {
  override fun update(sellIn: Int): Int =
      sellIn
}

class NormalSellInUpdater : SellInUpdater {
  override fun update(sellIn: Int): Int =
      sellIn - 1
}
