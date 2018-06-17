open class Item(
    private val name: String,
    var sellIn: Int,
    var quality: Int,
    val qualityUpdater: QualityUpdater = NormalQualityUpdater()
) {
  companion object {
    @JvmStatic val MAX_QUALITY = 50
  }

  open fun updateQuality() {
    sellIn -= 1
    quality = qualityUpdater.update(sellIn, quality)
  }

  override fun toString(): String =
      "$name / sell in $sellIn / quality of $quality"
}
