package quality

import store.Item.Companion.MAX_QUALITY

class BackstagePassesQualityUpdater : QualityUpdater {
  override fun update(sellIn: Int, quality: Int): Int {
    val pastConcert = sellIn < 0
    return if (pastConcert) {
      0

    } else if (!pastConcert && quality < MAX_QUALITY) {
      val updatedQuality = quality + when (sellIn) {
        in 0..4 -> 3
        in 5..9 -> 2
        else -> 1
      }
      Math.min(updatedQuality, MAX_QUALITY)

    } else {
      quality
    }
  }
}
