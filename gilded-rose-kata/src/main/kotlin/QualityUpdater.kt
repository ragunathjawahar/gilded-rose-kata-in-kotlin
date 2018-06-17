import Item.Companion.MAX_QUALITY

interface QualityUpdater {
  fun update(sellIn: Int, quality: Int): Int
}

class AgedBrieQualityUpdater : QualityUpdater {
  override fun update(sellIn: Int, quality: Int): Int {
    val newQuality = quality + if (sellIn >= 0) 1 else 2
    return Math.min(newQuality, MAX_QUALITY)
  }
}

class BackstagePassesQualityUpdater : QualityUpdater {
  override fun update(sellIn: Int, quality: Int): Int {
    val pastConcert = sellIn < 0
    return if (pastConcert) {
      0

    } else if (!pastConcert && quality < MAX_QUALITY) {
      val newQuality = quality + when (sellIn) {
        in 0..4 -> 3
        in 5..9 -> 2
        else -> 1
      }
      Math.min(newQuality, MAX_QUALITY)

    } else {
      quality
    }
  }
}

class ConjuredQualityUpdater : QualityUpdater {
  override fun update(sellIn: Int, quality: Int): Int {
    val newQuality = quality - if (sellIn > 0) 2 else 4
    return Math.max(0, newQuality)
  }
}

class NoopQualityUpdater : QualityUpdater {
  override fun update(sellIn: Int, quality: Int): Int =
      quality
}

class NormalQualityUpdater : QualityUpdater {
  override fun update(sellIn: Int, quality: Int): Int {
    val newQuality = quality - if (sellIn > 0) 1 else 2
    return Math.max(0, newQuality)
  }
}
