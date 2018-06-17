open class Item(
    private val name: String,
    var sellIn: Int,
    var quality: Int,
    val qualityUpdater: QualityUpdater = NormalQualityUpdater(),
    val sellInUpdater: SellInUpdater = NormalSellInUpdater()
) {
  companion object {
    @JvmStatic val MAX_QUALITY = 50
  }

  open fun updateQuality() {
    sellIn = sellInUpdater.update(sellIn)
    quality = qualityUpdater.update(sellIn, quality)
  }

  override fun toString(): String =
      "$name / sell in $sellIn / quality of $quality"
}
