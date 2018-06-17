open class Item(
    private val name: String,
    var sellIn: Int,
    var quality: Int
) {
  companion object {
    @JvmStatic val MAX_QUALITY = 50
  }

  open fun updateQuality() {
    sellIn -= 1
    quality -= if (sellIn > 0) 1 else 2

    if (quality < 0) {
      quality = 0
    }
  }

  override fun toString(): String =
      "$name / sell in $sellIn / quality of $quality"
}
