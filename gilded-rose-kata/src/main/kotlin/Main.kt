fun main(args: Array<String>) {
  val items = mutableListOf(
      dexterityVest(10, 20),
      agedBrie(2, 0),
      elixirOfTheMongoose(5, 7),
      sulfuras(),
      backstagePasses(15, 20),
      conjuredManaCake(15, 20)
  )

  val gildedRose = GildedRose(items)
  gildedRose.runFor(20)
}

private fun GildedRose.runFor(days: Int) {
  for (day in 1..days) {
    println("---- Day #$day ----")
    update()
    print()
    println()
  }
}

private fun GildedRose.print() =
    items.forEach { item -> println(item) }

fun agedBrie(sellIn: Int, quality: Int): Item =
    Item("Aged Brie", sellIn, quality, AgedBrieQualityUpdater())

fun backstagePasses(sellIn: Int, quality: Int): Item =
    Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality, BackstagePassesQualityUpdater())

fun dexterityVest(sellIn: Int, quality: Int): Item =
    Item("+5 Dexterity Vest", sellIn, quality)

fun elixirOfTheMongoose(sellIn: Int, quality: Int): Item =
    Item("Elixir of the Mongoose", sellIn, quality)

fun sulfuras(): Item =
    Item("Sulfuras, Hand of Ragnaros", 0, 80, NoopQualityUpdater(), NoopSellInUpdater())

fun conjuredManaCake(sellIn: Int, quality: Int): Item =
    Item("Conjured Mana Cake", sellIn, quality, ConjuredQualityUpdater())
