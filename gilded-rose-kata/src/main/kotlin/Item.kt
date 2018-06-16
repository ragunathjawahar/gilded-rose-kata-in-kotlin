fun agedBrie(sellIn: Int, quality: Int): Item =
    AgedBrie(sellIn, quality)

fun backstagePasses(sellIn: Int, quality: Int): Item =
    BackstagePasses(sellIn, quality)

fun dexterityVest(sellIn: Int, quality: Int): Item =
    DexterityVest(sellIn, quality)

fun elixirOfTheMongoose(sellIn: Int, quality: Int): Item =
    ElixirOfTheMongoose(sellIn, quality)

fun sulfuras(): Item =
    Sulfuras()

open class Item(
    val name: String,
    var sellIn: Int,
    var quality: Int
) {
  companion object {
    @JvmStatic val MAX_QUALITY = 50
  }

  open fun updateQuality() {
    sellIn -= 1
    if (quality > 0) {
      quality -= if (sellIn > 0) 1 else 2
    }
  }

  override fun toString(): String =
      "$name / sell in $sellIn / quality of $quality"
}
