const val MAX_QUALITY = 50

class GildedRose(val items: List<Item>) {
  constructor(item: Item) : this(listOf(item))

  fun updateQuality() {
    for (i in 0..items.size-1) {
      if (items[i].name != AGED_BRIE && items[i].name != BACKSTAGE_PASSES) {
        if (items[i].quality > 0) {
          if (items[i].name != SULFURAS) {
            items[i].quality = items[i].quality - 1
          }
        }
      }
      else {
        if (items[i].quality < MAX_QUALITY) {
          items[i].quality = items[i].quality + 1

          if (items[i].name == BACKSTAGE_PASSES) {
            if (items[i].sellIn < 11) {
              if (items[i].quality < MAX_QUALITY) {
                items[i].quality = items[i].quality + 1
              }
            }

            if (items[i].sellIn < 6) {
              if (items[i].quality < MAX_QUALITY) {
                items[i].quality = items[i].quality + 1
              }
            }
          }
        }
      }

      if (items[i].name != SULFURAS) {
        items[i].sellIn = items[i].sellIn - 1
      }

      if (items[i].sellIn < 0) {
        if (items[i].name != AGED_BRIE) {
          if (items[i].name != BACKSTAGE_PASSES) {
            if (items[i].quality > 0) {
              if (items[i].name != SULFURAS) {
                items[i].quality = items[i].quality - 1
              }
            }
          }
          else {
            items[i].quality = items[i].quality - items[i].quality
          }
        }
        else {
          if (items[i].quality < MAX_QUALITY) {
            items[i].quality = items[i].quality + 1
          }
        }
      }
    }
  }
}
