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

  private fun updateQuality(item: Item) {
    if (item.name == AGED_BRIE) {
      agedBrieUpdateQuality(item)
      return
    } else if (item.name == BACKSTAGE_PASSES) {
      backstagePassesUpdateQuality(item)
      return
    }

    if (item.name != AGED_BRIE && item.name != BACKSTAGE_PASSES) {
      if (item.quality > 0) {
        if (item.name != SULFURAS) {
          item.quality = item.quality - 1
        }
      }
    } else {
      if (item.quality < MAX_QUALITY) {
        item.quality = item.quality + 1

        if (item.name == BACKSTAGE_PASSES) {
          if (item.sellIn < 11) {
            if (item.quality < MAX_QUALITY) {
              item.quality = item.quality + 1
            }
          }

          if (item.sellIn < 6) {
            if (item.quality < MAX_QUALITY) {
              item.quality = item.quality + 1
            }
          }
        }
      }
    }

    if (item.name != SULFURAS) {
      item.sellIn = item.sellIn - 1
    }

    if (item.sellIn < 0) {
      if (item.name != AGED_BRIE) {
        if (item.name != BACKSTAGE_PASSES) {
          if (item.quality > 0) {
            if (item.name != SULFURAS) {
              item.quality = item.quality - 1
            }
          }
        } else {
          item.quality = item.quality - item.quality
        }
      } else {
        if (item.quality < MAX_QUALITY) {
          item.quality = item.quality + 1
        }
      }
    }
  }
}
