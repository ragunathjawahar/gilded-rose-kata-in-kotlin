const val MAX_QUALITY = 50

class GildedRose(val items: List<Item>) {
  constructor(item: Item) : this(listOf(item))

  fun updateQuality() {
    items.forEach(::updateQuality)
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

  private fun updateQuality(item: Item) {
    when {
      item is AgedBrie -> item.updateQuality()
      item.name == BACKSTAGE_PASSES -> backstagePassesUpdateQuality(item)
      item is Sulfuras -> item.updateQuality()
      else -> item.updateQuality()
    }
  }
}
