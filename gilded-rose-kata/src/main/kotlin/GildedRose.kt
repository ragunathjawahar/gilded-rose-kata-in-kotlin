import store.Item

class GildedRose(val items: List<Item>) {
  fun update() =
      items.forEach { it.update() }
}
