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
    updateQuality(GildedRose(items), days)

    // then
    items.forEach {
      assertThat(it.quality)
          .isAtLeast(0)
    }
  }

  @Test
  fun `quality of an item decreases by 1`() {
    // given
    val elixirOfTheMongoose = elixirOfTheMongoose(5, 10)
    val gildedRose = GildedRose(elixirOfTheMongoose)

    // when
    updateQuality(gildedRose, 1)

    // then
    assertThat(elixirOfTheMongoose.sellIn).isEqualTo(4)
    assertThat(elixirOfTheMongoose.quality).isEqualTo(9)
  }

  @Test
  fun `quality of an item degrades by 2 after the sell-in`() {
    // given
    val agedBrie = dexterityVest(0, MAX_QUALITY)
    val gildedRose = GildedRose(agedBrie)

    // when
    updateQuality(gildedRose, 20)

    // then
    assertThat(agedBrie.sellIn).isEqualTo(-20)
    assertThat(agedBrie.quality).isEqualTo(10)
  }

  @Test
  fun `aged brie increases in quality by 1, the older is gets`() {
    // given
    val agedBrie = agedBrie(5, 47)
    val gildedRose = GildedRose(agedBrie)

    // when
    updateQuality(gildedRose, 2)

    // then
    assertThat(agedBrie.sellIn)
        .isEqualTo(3)
    assertThat(agedBrie.quality)
        .isEqualTo(49)
  }

  @Test
  fun `aged brie quality does not increase beyond maximum quality`() {
    val agedBrie = agedBrie(10, MAX_QUALITY)
    val gildedRose = GildedRose(agedBrie)

    // when
    updateQuality(gildedRose, 8)

    // then
    assertThat(agedBrie.sellIn).isEqualTo(2)
    assertThat(agedBrie.quality).isEqualTo(MAX_QUALITY)
  }

  @Test
  fun `sulfuras, never has to be sold or decreases in quality`() {
    // given
    val sulfuras = sulfuras()
    val originalSellIn = sulfuras.sellIn
    val originalQuality = sulfuras.quality

    // when
    updateQuality(GildedRose(sulfuras), 100)

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
    updateQuality(gildedRose, 1)

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(9)
    assertThat(backstagePasses.quality).isEqualTo(26)

    // when
    updateQuality(gildedRose, 3)

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
    updateQuality(gildedRose, 1)

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(4)
    assertThat(backstagePasses.quality).isEqualTo(30)

    // when
    updateQuality(gildedRose, 3)

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(1)
    assertThat(backstagePasses.quality).isEqualTo(39)
  }

  @Test
  fun `backstage passes, does not increase quality beyond max quality`() {
    // given
    val backstagePasses = backstagePasses(10, MAX_QUALITY)
    val gildedRose = GildedRose(backstagePasses)

    // when
    updateQuality(gildedRose, 2)

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(8)
    assertThat(backstagePasses.quality).isEqualTo(MAX_QUALITY)
  }

  @Test
  fun `backstage passes, quality falls down to zero after the concert`() {
    // given
    val backstagePasses = backstagePasses(0, MAX_QUALITY)
    val gildedRose = GildedRose(backstagePasses)

    // when
    updateQuality(gildedRose, 1)

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
    updateQuality(gildedRose, 4)

    // then
    assertThat(backstagePasses.quality).isEqualTo(MAX_QUALITY)
  }

  private fun updateQuality(gildedRose: GildedRose, days: Int) {
    for (day in 1..days) {
      gildedRose.updateQuality()
    }
  }
}
