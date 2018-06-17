class GildedRose(val items: List<Item>) {
  fun updateQuality() =
      items.forEach { it.update() }
}
