class BackstagePasses(
    sellIn: Int,
    quality: Int
) : Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality, BackstagePassesQualityUpdater()) {
  override fun updateQuality() {
    sellIn = sellInUpdater.update(sellIn)
    quality = qualityUpdater.update(sellIn, quality)
  }
}
