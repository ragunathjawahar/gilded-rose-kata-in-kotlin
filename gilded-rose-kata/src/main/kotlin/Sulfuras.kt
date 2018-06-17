class Sulfuras : Item("Sulfuras, Hand of Ragnaros", 0, 80, NoopQualityUpdater()) {
  override fun updateQuality() {
    quality = qualityUpdater.update(sellIn, quality)
  }
}
