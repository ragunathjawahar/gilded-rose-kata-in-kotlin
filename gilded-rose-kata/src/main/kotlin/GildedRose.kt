class GildedRose(val items: List<Item>) {
  constructor(item: Item) : this(listOf(item))

  fun updateQuality() {
    items.forEach { it.updateQuality() }
  }
}
