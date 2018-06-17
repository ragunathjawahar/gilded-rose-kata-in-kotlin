package quality

import store.Item.Companion.MAX_QUALITY

class AgedBrieQualityUpdater : QualityUpdater {
  override fun update(sellIn: Int, quality: Int): Int {
    val updatedQuality = quality + if (sellIn >= 0) 1 else 2
    return Math.min(updatedQuality, MAX_QUALITY)
  }
}
