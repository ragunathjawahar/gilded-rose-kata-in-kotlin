import quality.NormalQualityUpdater
import quality.QualityUpdater
import sellin.NormalSellInUpdater
import sellin.SellInUpdater

class Item(
    private val name: String,
    var sellIn: Int,
    var quality: Int,
    private val qualityUpdater: QualityUpdater = NormalQualityUpdater(),
    private val sellInUpdater: SellInUpdater = NormalSellInUpdater()
) {
  companion object {
    @JvmStatic val MAX_QUALITY = 50
  }

  fun update() {
    sellIn = sellInUpdater.update(sellIn)
    quality = qualityUpdater.update(sellIn, quality)
  }

  override fun toString(): String =
      "$name / sell in $sellIn / quality of $quality"
}
