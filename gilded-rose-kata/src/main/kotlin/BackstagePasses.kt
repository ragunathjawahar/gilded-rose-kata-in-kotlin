class BackstagePasses(
    sellIn: Int,
    quality: Int
) : Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality) {
  override fun updateQuality() {
    sellIn -= 1

    val pastConcert = sellIn < 0
    if (pastConcert) {
      quality = 0

    } else if (!pastConcert && quality < MAX_QUALITY) {
      quality += when (sellIn) {
        in 0..4 -> 3
        in 5..9 -> 2
        else -> 1
      }
      quality = Math.min(quality, MAX_QUALITY)
    }
  }
}
