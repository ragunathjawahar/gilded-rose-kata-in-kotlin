package store

import quality.DefaultQualityUpdater
import quality.QualityUpdater
import sellin.DefaultSellInUpdater
import sellin.SellInUpdater

class Item(
    private val name: String,
    var sellIn: Int,
    var quality: Int,
    private val qualityUpdater: QualityUpdater = DefaultQualityUpdater(),
    private val sellInUpdater: SellInUpdater = DefaultSellInUpdater()
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
