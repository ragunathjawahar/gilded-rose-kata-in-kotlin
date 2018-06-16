private const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"

class BackstagePasses(sellIn: Int, quality: Int) : Item(BACKSTAGE_PASSES, sellIn, quality) {
  override fun updateQuality() {
    if (sellIn >= 0 && quality < MAX_QUALITY) {
      quality += when {
        sellIn in 0..5 -> 3
        sellIn in 6..10 -> 2
        else -> 1
      }
    }

    sellIn -= 1
    if (sellIn < 0) {
      quality = 0
    }
  }
}
