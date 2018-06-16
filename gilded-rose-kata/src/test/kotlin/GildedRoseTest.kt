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
}
