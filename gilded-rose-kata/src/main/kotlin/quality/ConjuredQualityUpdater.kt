package quality

class ConjuredQualityUpdater : QualityUpdater {
  override fun update(sellIn: Int, quality: Int): Int {
    val newQuality = quality - if (sellIn > 0) 2 else 4
    return Math.max(0, newQuality)
  }
}
