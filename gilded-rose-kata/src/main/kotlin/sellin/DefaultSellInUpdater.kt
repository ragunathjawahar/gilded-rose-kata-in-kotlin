package sellin

class DefaultSellInUpdater : SellInUpdater {
  override fun update(sellIn: Int): Int =
      sellIn - 1
}
