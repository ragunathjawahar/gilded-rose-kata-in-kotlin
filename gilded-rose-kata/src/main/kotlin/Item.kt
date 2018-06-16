const val AGED_BRIE = "Aged Brie"
const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
const val SULFURAS = "Sulfuras, Hand of Ragnaros"

const val SULFURAS_QUALITY = 80

fun agedBrie(sellIn: Int, quality: Int): Item =
    Item(AGED_BRIE, sellIn, quality)

fun backstagePasses(sellIn: Int, quality: Int): Item =
    Item(BACKSTAGE_PASSES, sellIn, quality)

fun dexterityVest(sellIn: Int, quality: Int): Item =
    Item("+5 Dexterity Vest", sellIn, quality)

fun elixirOfTheMongoose(sellIn: Int, quality: Int): Item =
    Item("Elixir of the Mongoose", sellIn, quality)

fun sulfuras(): Item =
    Item(SULFURAS, 0, SULFURAS_QUALITY)

data class Item(
    val name: String,
    var sellIn: Int,
    var quality: Int
) {
  override fun toString() = "$name / sell in $sellIn / quality of $quality"
}
