package display

import store.Item

class DefaultFormatter : Formatter() {
  override fun display(day: Int, items: List<Item>) {
    println("---- Day #$day ----")
    items.forEach { item -> println(item) }
    println()
  }
}
