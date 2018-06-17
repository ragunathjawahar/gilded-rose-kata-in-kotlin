package quality

class NormalQualityUpdater : QualityUpdater {
  override fun update(sellIn: Int, quality: Int): Int {
    val newQuality = quality - if (sellIn > 0) 1 else 2
    return Math.max(0, newQuality)
  }
}
