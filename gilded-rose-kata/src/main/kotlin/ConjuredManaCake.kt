class ConjuredManaCake(
    sellIn: Int,
    quality: Int
) : Item("Conjured Mana Cake", sellIn, quality) {
  override fun updateQuality() {
    sellIn -= 1
    quality -= if (sellIn > 0) 2 else 4

    if (quality < 0) {
      quality = 0
    }
  }
}
