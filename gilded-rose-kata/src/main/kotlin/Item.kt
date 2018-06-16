const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"

fun agedBrie(sellIn: Int, quality: Int): Item =
    AgedBrie(sellIn, quality)

fun backstagePasses(sellIn: Int, quality: Int): Item =
    Item(BACKSTAGE_PASSES, sellIn, quality)

fun dexterityVest(sellIn: Int, quality: Int): Item =
    Item("+5 Dexterity Vest", sellIn, quality)

fun elixirOfTheMongoose(sellIn: Int, quality: Int): Item =
    Item("Elixir of the Mongoose", sellIn, quality)

fun sulfuras(): Item =
    Sulfuras()

open class Item(
    val name: String,
    var sellIn: Int,
    var quality: Int
) {
  open fun updateQuality() {
    sellIn -= 1
    if (quality > 0) {
      quality -= if (sellIn > 0) 1 else 2
    }
  }

  override fun toString(): String =
      "$name / sell in $sellIn / quality of $quality"
}
