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
  fun `backstage passes, quality increases by 1 when there are more than 10 days`() {
    // given
    val backstagePasses = backstagePasses(15, 20)
    val gildedRose = GildedRose(backstagePasses)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(14)
    assertThat(backstagePasses.quality).isEqualTo(21)
  }

  @Test
  fun `backstage passes, quality increases before the concert event if it is already 0`() {
    // given
    val backstagePasses = backstagePasses(11, 0)
    val gildedRose = GildedRose(backstagePasses)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(10)
    assertThat(backstagePasses.quality).isEqualTo(1)
  }

  @Test
  fun `backstage passes, quality increases by 2 between 10 to 6 days`() {
    // given
    val backstagePasses = backstagePasses(10, 1)
    val gildedRose = GildedRose(backstagePasses)

    // when
    for (i in 10 downTo 6) {
      gildedRose.updateQuality()
    }

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(5)
    assertThat(backstagePasses.quality).isEqualTo(11)
  }

  @Test
  fun `backstage passes, quality increases by 3 between 5 to 1 days`() {
    // given
    val backstagePasses = backstagePasses(5, 1)
    val gildedRose = GildedRose(backstagePasses)

    // when
    for (i in 5 downTo 1) {
      gildedRose.updateQuality()
    }

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(0)
    assertThat(backstagePasses.quality).isEqualTo(16)
  }


  @Test
  fun `backstage passes, quality does not increase beyond max quality if sell-in is more than 10 days`() {
    // given
    val backstagePasses = backstagePasses(24, MAX_QUALITY)
    val gildedRose = GildedRose(backstagePasses)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(23)
    assertThat(backstagePasses.quality).isEqualTo(MAX_QUALITY)
  }

  @Test
  fun `backstage passes, quality does not increase beyond max quality between 10 to 6 days`() {
    // given
    val backstagePasses = backstagePasses(10, MAX_QUALITY)
    val gildedRose = GildedRose(backstagePasses)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(9)
    assertThat(backstagePasses.quality).isEqualTo(MAX_QUALITY)
  }

  @Test
  fun `backstage passes, quality does not increase beyond max quality between 5 to 1 days`() {
    // given
    val backstagePasses = backstagePasses(4, MAX_QUALITY)
    val gildedRose = GildedRose(backstagePasses)

    // when
    gildedRose.updateQuality()

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(3)
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
  fun `backstage passes, quality does not degrade below 0 after the concert`() {
    // given
    val backstagePasses = backstagePasses(2, MAX_QUALITY)
    val gildedRose = GildedRose(backstagePasses)

    // when
    for (i in 2 downTo -3) {
      gildedRose.updateQuality()
    }

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(-4)
    assertThat(backstagePasses.quality).isEqualTo(0)
  }

  private fun updateQuality(gildedRose: GildedRose, days: Int) {
    for (day in 1..days) {
      gildedRose.updateQuality()
    }
  }
}
