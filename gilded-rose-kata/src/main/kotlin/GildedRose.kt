const val MAX_QUALITY = 50

class GildedRose(val items: List<Item>) {
  constructor(item: Item) : this(listOf(item))

  fun updateQuality() {
    items.forEach { item ->
      if (item.name != AGED_BRIE && item.name != BACKSTAGE_PASSES) {
        if (item.quality > 0) {
          if (item.name != SULFURAS) {
            item.quality = item.quality - 1
          }
        }
      }
      else {
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
          }
          else {
            item.quality = item.quality - item.quality
          }
        }
        else {
          if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1
          }
        }
      }
    }
  }
}
