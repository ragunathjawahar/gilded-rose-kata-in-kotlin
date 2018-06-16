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
  fun `normal item's quality degrades by 1`() {
    // given
    val dragonEgg = Item("Dragon Egg", 10, 40)
    val gildedRose = GildedRose(dragonEgg)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(dragonEgg.sellIn).isEqualTo(9)
    assertThat(dragonEgg.quality).isEqualTo(39)
  }

  @Test
  fun `normal item quality does not degrade below 0`() {
    // given
    val dragonEgg = Item("Dragon Egg", 0, 0)
    val gildedRose = GildedRose(dragonEgg)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(dragonEgg.sellIn).isEqualTo(-1)
    assertThat(dragonEgg.quality).isEqualTo(0)
  }

  @Test
  fun `normal item's quality degrades by 2 once the sell-in date passes`() {
    // given
    val dragonEgg = Item("Dragon Egg", 0, 20)
    val gildedRose = GildedRose(dragonEgg)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(dragonEgg.sellIn).isEqualTo(-1)
    assertThat(dragonEgg.quality).isEqualTo(18)
  }

  @Test
  fun `aged brie's quality increases by 1`() {
    // given
    val agedBrie = agedBrie(10, 10)
    val gildedRose = GildedRose(agedBrie)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(agedBrie.sellIn).isEqualTo(9)
    assertThat(agedBrie.quality).isEqualTo(11)
  }

  @Test
  fun `aged brie's quality increases by 2 beyond the sell-in date`() {
    // given
    val agedBrie = agedBrie(0, 20)
    val gildedRose = GildedRose(agedBrie)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(agedBrie.sellIn).isEqualTo(-1)
    assertThat(agedBrie.quality).isEqualTo(22)
  }

  @Test
  fun `aged brie's quality increase even if it is 0`() {
    // given
    val agedBrie = agedBrie(10, 0)
    val gildedRose = GildedRose(agedBrie)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(agedBrie.sellIn).isEqualTo(9)
    assertThat(agedBrie.quality).isEqualTo(1)
  }

  @Test
  fun `aged brie's quality does not increase beyond max quality before the sell-in date`() {
    // given
    val agedBrie = agedBrie(10, MAX_QUALITY)
    val gildedRose = GildedRose(agedBrie)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(agedBrie.sellIn).isEqualTo(9)
    assertThat(agedBrie.quality).isEqualTo(MAX_QUALITY)
  }

  @Test
  fun `aged brie's quality does not increase beyond max quality after the sell-in date`() {
    // given
    val agedBrie = agedBrie(-5, MAX_QUALITY)
    val gildedRose = GildedRose(agedBrie)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(agedBrie.sellIn).isEqualTo(-6)
    assertThat(agedBrie.quality).isEqualTo(MAX_QUALITY)
  }

  @Test
  fun `aged brie's quality is never negative`() {
    // given
    val agedBrie = agedBrie(0, 0)
    val gildedRose = GildedRose(agedBrie)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(agedBrie.sellIn).isEqualTo(-1)
    assertThat(agedBrie.quality).isEqualTo(2)
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
