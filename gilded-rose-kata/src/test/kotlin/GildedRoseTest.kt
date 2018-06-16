import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GildedRoseTest {
  @Test
  fun `quality of an item is never negative`() {
    val days = 100

    val items = listOf(
        dexterityVest(days, MAX_QUALITY),
        agedBrie(days, MAX_QUALITY),
        elixirOfTheMongoose(days, MAX_QUALITY),
        sulfuras(days, 80),
        backstagePasses(days, MAX_QUALITY)
    )

    GildedRose(items).runFor(days)

    items.forEach {
      assertThat(it.quality)
          .isAtLeast(0)
    }
  }

  @Test
  fun `aged brie increases in quality, the older is gets but is never more than the maximum quality`() {
    val agedBrie = agedBrie(5, 47)

    val gildedRose = GildedRose(listOf(agedBrie))

    gildedRose.runFor(2)
    assertThat(agedBrie.sellIn).isEqualTo(3)
    assertThat(agedBrie.quality).isEqualTo(49)

    gildedRose.runFor(8)
    assertThat(agedBrie.sellIn).isEqualTo(-5)
    assertThat(agedBrie.quality).isEqualTo(50)
  }
}
