import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GildedRoseTest {
  @Test
  fun `quality of an item is never negative`() {
    // given
    val days = 100
    val items = listOf(
        dexterityVest(days, MAX_QUALITY),
        agedBrie(days, MAX_QUALITY),
        elixirOfTheMongoose(days, MAX_QUALITY),
        sulfuras(),
        backstagePasses(days, MAX_QUALITY)
    )

    // when
    GildedRose(items).runFor(days)

    // then
    items.forEach {
      assertThat(it.quality)
          .isAtLeast(0)
    }
  }

  @Test
  fun `aged brie increases in quality, the older is gets`() {
    // given
    val agedBrie = agedBrie(5, 47)
    val gildedRose = GildedRose(agedBrie)

    // when
    gildedRose.runFor(2)

    // then
    assertThat(agedBrie.sellIn)
        .isEqualTo(3)
    assertThat(agedBrie.quality)
        .isEqualTo(49)
  }

  @Test
  fun `aged brie quality does not increase beyond maximum quality`() {
    val agedBrie = agedBrie(10, 50)
    val gildedRose = GildedRose(agedBrie)

    // when
    gildedRose.runFor(8)

    // then
    assertThat(agedBrie.sellIn).isEqualTo(2)
    assertThat(agedBrie.quality).isEqualTo(50)
  }

  @Test
  fun `sulfuras, never has to be sold or decreases in quality`() {
    // given
    val sulfuras = sulfuras()
    val originalSellIn = sulfuras.sellIn
    val originalQuality = sulfuras.quality

    // when
    GildedRose(sulfuras).runFor(100)

    // then
    assertThat(sulfuras.sellIn).isEqualTo(originalSellIn)
    assertThat(sulfuras.quality).isEqualTo(originalQuality)
  }

  @Test
  fun `backstage passes, increases quality by 2 between 6 to 10 days of sell-in`() {
    // given
    val backstagePasses = backstagePasses(10, 24)
    val gildedRose = GildedRose(backstagePasses)

    // when
    gildedRose.runFor(1)

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(9)
    assertThat(backstagePasses.quality).isEqualTo(26)

    // when
    gildedRose.runFor(3)

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(6)
    assertThat(backstagePasses.quality).isEqualTo(32)
  }

  @Test
  fun `backstage passes, increases quality by 3 between 1 to 5 days of sell-in`() {
    // given
    val backstagePasses = backstagePasses(5, 27)
    val gildedRose = GildedRose(backstagePasses)

    // when
    gildedRose.runFor(1)

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(4)
    assertThat(backstagePasses.quality).isEqualTo(30)

    // when
    gildedRose.runFor(3)

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(1)
    assertThat(backstagePasses.quality).isEqualTo(39)
  }

  @Test
  fun `backstage passes, quality falls down to zero after the concert`() {
    // given
    val backstagePasses = backstagePasses(0, MAX_QUALITY)
    val gildedRose = GildedRose(backstagePasses)

    // when
    gildedRose.runFor(1)

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(-1)
    assertThat(backstagePasses.quality).isEqualTo(0)
  }

  @Test
  fun `backstage passes, quality does not increase beyond maximum quality`() {
    // given
    val backstagePasses = backstagePasses(5, MAX_QUALITY)
    val gildedRose = GildedRose(backstagePasses)

    // when
    gildedRose.runFor(4)

    // then
    assertThat(backstagePasses.quality).isEqualTo(MAX_QUALITY)
  }
}
