class AgedBrie(
    sellIn: Int,
    quality: Int
) : Item("Aged Brie" ,sellIn, quality, AgedBrieQualityUpdater()) {
  override fun updateQuality() {
    sellIn--
    quality = qualityUpdater.update(sellIn, quality)
  }
}
