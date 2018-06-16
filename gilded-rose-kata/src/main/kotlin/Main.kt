fun main(args: Array<String>) {
  val items = mutableListOf(
      dexterityVest(10, 20),
      agedBrie(2, 0),
      elixirOfTheMongoose(5, 7),
      sulfuras(),
      backstagePasses(15, 20)
      // Item("Conjured Mana Cake", 15, 20)
  )

  val gildedRose = GildedRose(items)
  gildedRose.runFor(20)
}

fun GildedRose.runFor(days: Int) {
  for (day in 1..days) {
    println("---- Day #$day ----")
    updateQuality()
    print()
    println()
  }
}

private fun GildedRose.print() =
    items.forEach { item -> println(item) }
