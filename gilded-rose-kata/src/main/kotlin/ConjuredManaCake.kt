class ConjuredManaCake(
    sellIn: Int,
    quality: Int
) : Item("Conjured Mana Cake", sellIn, quality, ConjuredQualityUpdater()) {
  override fun updateQuality() {
    sellIn = sellInUpdater.update(sellIn)
    quality = qualityUpdater.update(sellIn, quality)
  }
}
