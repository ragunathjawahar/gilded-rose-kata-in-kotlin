package quality

class NoopQualityUpdater : QualityUpdater {
  override fun update(sellIn: Int, quality: Int): Int =
      quality
}
