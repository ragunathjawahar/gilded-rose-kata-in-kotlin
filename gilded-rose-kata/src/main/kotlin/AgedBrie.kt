class AgedBrie(
    sellIn: Int,
    quality: Int
) : Item("Aged Brie" ,sellIn, quality) {
  override fun updateQuality() {
    sellIn -=1
    if (quality < MAX_QUALITY) {
      val qualityToIncrease = if (sellIn < 0) 2 else 1
      quality += qualityToIncrease
    }
  }
}
