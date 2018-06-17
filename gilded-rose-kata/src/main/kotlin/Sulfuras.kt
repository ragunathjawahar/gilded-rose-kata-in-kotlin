class Sulfuras : Item("Sulfuras, Hand of Ragnaros", 0, 80, NoopQualityUpdater(), NoopSellInUpdater()) {
  override fun updateQuality() {
    sellIn = sellInUpdater.update(sellIn)
    quality = qualityUpdater.update(sellIn, quality)
  }
}
