package quality

class DefaultQualityUpdater : QualityUpdater {
  override fun update(sellIn: Int, quality: Int): Int {
    val updatedQuality = quality - if (sellIn > 0) 1 else 2
    return Math.max(0, updatedQuality)
  }
}
