package quality

interface QualityUpdater {
  fun update(sellIn: Int, quality: Int): Int
}
