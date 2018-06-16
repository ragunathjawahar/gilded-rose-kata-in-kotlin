const val MAX_QUALITY = 50

class GildedRose(val items: List<Item>) {
  constructor(item: Item) : this(listOf(item))

  fun updateQuality() {
    items.forEach(::updateQuality)
  }

  private fun agedBrieUpdateQuality(item: Item) {
    item.sellIn -=1
    if (item.quality < MAX_QUALITY) {
      val qualityToIncrease = if (item.sellIn < 0) 2 else 1
      item.quality += qualityToIncrease
    }
  }

  private fun backstagePassesUpdateQuality(item: Item) {
    if (item.sellIn >= 0 && item.quality < MAX_QUALITY) {
      item.quality += when {
        item.sellIn in 0..5 -> 3
        item.sellIn in 6..10 -> 2
        else -> 1
      }
    }

    item.sellIn -= 1
    if (item.sellIn < 0) {
      item.quality = 0
    }
  }

  private fun sulfurasUpdateQuality(item: Item) {
    /* do nothing */
  }

  private fun normalUpdateQuality(item: Item) {
    item.sellIn -= 1
    if (item.quality > 0) {
      item.quality -= if (item.sellIn > 0) 1 else 2
    }
  }

  private fun updateQuality(item: Item) {
    when {
      item.name == AGED_BRIE -> agedBrieUpdateQuality(item)
      item.name == BACKSTAGE_PASSES -> backstagePassesUpdateQuality(item)
      item.name == SULFURAS -> sulfurasUpdateQuality(item)
      else -> normalUpdateQuality(item)
    }
  }
}
