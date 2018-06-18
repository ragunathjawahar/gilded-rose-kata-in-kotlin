import com.google.common.truth.Truth.assertThat
import display.Formatter
import org.junit.Test
import store.CONJURED_MANA_CAKE
import store.DEXTERITY_VEST
import store.ELIXIR_OF_THE_MONGOOSE
import store.Item
import store.Item.Companion.MAX_QUALITY
import store.ItemFactory.Companion.agedBrie
import store.ItemFactory.Companion.backstagePasses
import store.ItemFactory.Companion.conjured
import store.ItemFactory.Companion.normal
import store.ItemFactory.Companion.sulfuras

class GildedRoseTest {
  @Test
  fun `quality of an item is never negative`() {
    // given
    val days = 100
    val items = listOf(
        normal(DEXTERITY_VEST, days, MAX_QUALITY),
        agedBrie(days, MAX_QUALITY),
        normal(ELIXIR_OF_THE_MONGOOSE, days, MAX_QUALITY),
        sulfuras(),
        backstagePasses(days, MAX_QUALITY)
    )
    val gildedRose = GildedRose(items, Formatter())

    // when
    gildedRose.runFor(100)

    // then
    items.forEach {
      assertThat(it.sellIn)
          .isAtMost(0)
      assertThat(it.quality)
          .isAtLeast(0)
    }
  }

  @Test
  fun `normal item's quality degrades by 1`() {
    // given
    val dragonEgg = normal("Dragon Egg", sellIn = 10, quality = 40)

    // when & then
    assertUpdate(
        dragonEgg,
        expectedSellIn = 9,
        expectedQuality = 39
    )
  }

  @Test
  fun `normal item quality does not degrade below 0`() {
    // given
    val dragonEgg = normal("Dragon Egg", sellIn = 0, quality = 0)

    // when & then
    assertUpdate(
        dragonEgg,
        expectedSellIn = -1,
        expectedQuality = 0
    )
  }

  @Test
  fun `normal item's quality degrades by 2 once the sell-in date passes`() {
    // given
    val dragonEgg = normal("Dragon Egg", sellIn = 0, quality = 20)

    // when & then
    assertUpdate(
        dragonEgg,
        expectedSellIn = -1,
        expectedQuality = 18
    )
  }

  @Test
  fun `normal item's quality should not be less than 0 when it degrades by 2 after the sell-in date`() {
    // given
    val dragonEgg = normal("Dragon Egg", sellIn = 0, quality = 1)

    // when & then
    assertUpdate(
        dragonEgg,
        expectedSellIn = -1,
        expectedQuality = 0
    )
  }

  @Test
  fun `aged brie's quality increases by 1`() {
    // given
    val agedBrie = agedBrie(sellIn = 10, quality = 10)

    // when & then
    assertUpdate(
        agedBrie,
        expectedSellIn = 9,
        expectedQuality = 11
    )
  }

  @Test
  fun `aged brie's quality increases by 2 beyond the sell-in date`() {
    // given
    val agedBrie = agedBrie(sellIn = 0, quality = 20)

    // when & then
    assertUpdate(
        agedBrie,
        expectedSellIn = -1,
        expectedQuality = 22
    )
  }

  @Test
  fun `aged brie's quality increase even if it is 0`() {
    // given
    val agedBrie = agedBrie(sellIn = 10, quality = 0)

    // when & then
    assertUpdate(
        agedBrie,
        expectedSellIn = 9,
        expectedQuality = 1
    )
  }

  @Test
  fun `aged brie's quality does not increase beyond max quality before the sell-in date`() {
    // given
    val agedBrie = agedBrie(sellIn = 10, quality = MAX_QUALITY)

    // when & then
    assertUpdate(
        agedBrie,
        expectedSellIn = 9,
        expectedQuality = MAX_QUALITY
    )
  }

  @Test
  fun `aged brie's quality does not increase beyond max quality after the sell-in date`() {
    // given
    val agedBrie = agedBrie(sellIn = -5, quality = 49)

    // when & then
    assertUpdate(
        agedBrie,
        expectedSellIn = -6,
        expectedQuality = MAX_QUALITY
    )
  }

  @Test
  fun `aged brie's quality is never negative`() {
    // given
    val agedBrie = agedBrie(sellIn = 0, quality = 0)

    // when & then
    assertUpdate(
        agedBrie,
        expectedSellIn = -1,
        expectedQuality = 2
    )
  }

  @Test
  fun `sulfuras, never has to be sold or decreases in quality`() {
    // given
    val sulfuras = sulfuras()
    val originalSellIn = sulfuras.sellIn
    val originalQuality = sulfuras.quality

    // when
    for (i in 100 downTo 0) {
      sulfuras.update()
    }

    // then
    assertThat(sulfuras.sellIn).isEqualTo(originalSellIn)
    assertThat(sulfuras.quality).isEqualTo(originalQuality)
  }

  @Test
  fun `backstage passes, quality increases by 1 when there are more than 10 days`() {
    // given
    val backstagePasses = backstagePasses(sellIn = 15, quality = 20)

    // when & then
    assertUpdate(
        backstagePasses,
        expectedSellIn = 14,
        expectedQuality = 21
    )
  }

  @Test
  fun `backstage passes, quality increases before the concert event if it is already 0`() {
    // given
    val backstagePasses = backstagePasses(sellIn = 11, quality = 0)

    // when & then
    assertUpdate(
        backstagePasses,
        expectedSellIn = 10,
        expectedQuality = 1
    )
  }

  @Test
  fun `backstage passes, quality increases by 2 between 10 to 6 days`() {
    // given
    val backstagePasses = backstagePasses(sellIn = 10, quality = 1)

    // when
    for (i in 10 downTo 6) {
      backstagePasses.update()
    }

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(5)
    assertThat(backstagePasses.quality).isEqualTo(11)
  }

  @Test
  fun `backstage passes, quality increases by 3 between 5 to 1 days`() {
    // given
    val backstagePasses = backstagePasses(sellIn = 5, quality = 1)

    // when
    for (i in 5 downTo 1) {
      backstagePasses.update()
    }

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(0)
    assertThat(backstagePasses.quality).isEqualTo(16)
  }


  @Test
  fun `backstage passes, quality does not increase beyond max quality if sell-in is more than 10 days`() {
    // given
    val backstagePasses = backstagePasses(sellIn = 24, quality = MAX_QUALITY)

    // when & then
    assertUpdate(
        backstagePasses,
        expectedSellIn = 23,
        expectedQuality = MAX_QUALITY
    )
  }

  @Test
  fun `backstage passes, quality does not increase beyond max quality between 10 to 6 days`() {
    // given
    val backstagePasses = backstagePasses(sellIn = 10, quality = MAX_QUALITY)

    // when & then
    assertUpdate(
        backstagePasses,
        expectedSellIn = 9,
        expectedQuality = MAX_QUALITY
    )
  }

  @Test
  fun `backstage passes, quality does not increase beyond max quality between 5 to 1 days`() {
    // given
    val backstagePasses = backstagePasses(sellIn = 4, quality = MAX_QUALITY)

    // when & then
    assertUpdate(
        backstagePasses,
        expectedSellIn = 3,
        expectedQuality = MAX_QUALITY
    )
  }

  @Test
  fun `backstage passes, quality falls down to zero after the concert`() {
    // given
    val backstagePasses = backstagePasses(sellIn = 0, quality = MAX_QUALITY)

    // when & then
    assertUpdate(
        backstagePasses,
        expectedSellIn = -1,
        expectedQuality = 0
    )
  }

  @Test
  fun `backstage passes quality does not degrade below 0 after the concert`() {
    // given
    val backstagePasses = backstagePasses(sellIn = 2, quality = MAX_QUALITY)

    // when
    for (i in 1..6) {
      backstagePasses.update()
    }

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(-4)
    assertThat(backstagePasses.quality).isEqualTo(0)
  }

  @Test
  fun `conjured item quality degrades by 2 before sell-in date`() {
    // given
    val manaCake = conjured(CONJURED_MANA_CAKE, sellIn = 10, quality = 12)

    // when & then
    assertUpdate(
        manaCake,
        expectedSellIn = 9,
        expectedQuality = 10
    )
  }

  @Test
  fun `conjured item quality degrades by 4 after the sell-in date`() {
    // given
    val manaCake = conjured(CONJURED_MANA_CAKE, sellIn = 0, quality = 10)

    // when & then
    assertUpdate(
        manaCake,
        expectedSellIn = -1,
        expectedQuality = 6
    )
  }

  @Test
  fun `conjured item quality does not degrade below 0`() {
    // given
    val manaCake = conjured(CONJURED_MANA_CAKE, sellIn = 0, quality = 0)

    // when & then
    assertUpdate(
        manaCake,
        expectedSellIn = -1,
        expectedQuality = 0
    )
  }

  private fun assertUpdate(
      item: Item,
      expectedSellIn: Int,
      expectedQuality: Int
  ) {
    item.update()

    assertThat(item.sellIn).isEqualTo(expectedSellIn)
    assertThat(item.quality).isEqualTo(expectedQuality)
  }
}
