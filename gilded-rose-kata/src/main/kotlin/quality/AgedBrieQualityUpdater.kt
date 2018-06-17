package quality

import Item.Companion.MAX_QUALITY

class AgedBrieQualityUpdater : QualityUpdater {
  override fun update(sellIn: Int, quality: Int): Int {
    val newQuality = quality + if (sellIn >= 0) 1 else 2
    return Math.min(newQuality, MAX_QUALITY)
  }
}
