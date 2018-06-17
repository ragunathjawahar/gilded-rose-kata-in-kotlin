import store.DEXTERITY_VEST
import store.ELIXIR_OF_THE_MONGOOSE
import store.ItemFactory.Companion.agedBrie
import store.ItemFactory.Companion.backstagePasses
import store.ItemFactory.Companion.conjured
import store.ItemFactory.Companion.normal
import store.ItemFactory.Companion.sulfuras

fun main(args: Array<String>) {
  val items = mutableListOf(
      normal(DEXTERITY_VEST, 10, 20),
      agedBrie(2, 0),
      normal(ELIXIR_OF_THE_MONGOOSE, 5, 7),
      sulfuras(),
      backstagePasses(15, 20),
      conjured("Conjured Mana Cake", 15, 20)
  )

  GildedRose(items).runFor(20)
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
