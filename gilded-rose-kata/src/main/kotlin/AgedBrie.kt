class AgedBrie(
    sellIn: Int,
    quality: Int
) : Item("Aged Brie" ,sellIn, quality) {
  override fun updateQuality() {
    sellIn--
    quality += if (sellIn >= 0) 1 else 2
    quality = Math.min(quality, MAX_QUALITY)
  }
}
