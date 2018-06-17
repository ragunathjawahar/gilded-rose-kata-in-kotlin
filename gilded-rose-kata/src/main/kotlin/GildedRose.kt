import display.DefaultFormatter
import display.Formatter
import store.Item

class GildedRose(
    private val items: List<Item>,
    private val formatter: Formatter = DefaultFormatter()
) {
  fun runFor(days: Int) {
    for (day in 1..days) {
      update()
      formatter.display(day, items)
    }
  }

  private fun update() =
      items.forEach { it.update() }
}
