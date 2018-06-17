fun main(args: Array<String>) {
  val items = mutableListOf(
      DexterityVest(10, 20),
      AgedBrie(2, 0),
      ElixirOfTheMongoose(5, 7),
      Sulfuras(),
      BackstagePasses(15, 20),
      ConjuredManaCake(15, 20)
  )

  val gildedRose = GildedRose(items)
  gildedRose.runFor(20)
}

private fun GildedRose.runFor(days: Int) {
  for (day in 1..days) {
    println("---- Day #$day ----")
    updateQuality()
    print()
    println()
  }
}

private fun GildedRose.print() =
    items.forEach { item -> println(item) }
