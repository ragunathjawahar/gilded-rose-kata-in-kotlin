package sellin

class NormalSellInUpdater : SellInUpdater {
  override fun update(sellIn: Int): Int =
      sellIn - 1
}
