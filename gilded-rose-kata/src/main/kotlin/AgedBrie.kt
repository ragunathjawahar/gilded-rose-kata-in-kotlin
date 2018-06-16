private const val AGED_BRIE = "Aged Brie"

class AgedBrie(sellIn: Int, quality: Int) : Item(AGED_BRIE ,sellIn, quality) {
  override fun updateQuality() {
    sellIn -=1
    if (quality < MAX_QUALITY) {
      val qualityToIncrease = if (sellIn < 0) 2 else 1
      quality += qualityToIncrease
    }
  }
}
