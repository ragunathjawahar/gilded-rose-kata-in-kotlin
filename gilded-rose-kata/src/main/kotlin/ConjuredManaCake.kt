class ConjuredManaCake(
    sellIn: Int,
    quality: Int
) : Item("Conjured Mana Cake", sellIn, quality, ConjuredQualityUpdater()) {
  override fun updateQuality() {
    sellIn -= 1
    quality = qualityUpdater.update(sellIn, quality)
  }
}
