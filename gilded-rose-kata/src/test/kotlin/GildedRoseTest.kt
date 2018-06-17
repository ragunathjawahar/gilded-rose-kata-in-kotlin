import Item.Companion.MAX_QUALITY
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GildedRoseTest {
  @Test
  fun `quality of an item is never negative`() {
    // given
    val days = 100
    val items = listOf(
        DexterityVest(days, MAX_QUALITY),
        AgedBrie(days, MAX_QUALITY),
        ElixirOfTheMongoose(days, MAX_QUALITY),
        Sulfuras(),
        BackstagePasses(days, MAX_QUALITY)
    )
    val gildedRose = GildedRose(items)

    // when
    for (i in 100 downTo 1) {
      gildedRose.updateQuality()
    }

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

    // when
    dragonEgg.updateQuality()

    // then
    assertThat(dragonEgg.sellIn).isEqualTo(9)
    assertThat(dragonEgg.quality).isEqualTo(39)
  }

  @Test
  fun `normal item quality does not degrade below 0`() {
    // given
    val dragonEgg = Item("Dragon Egg", 0, 0)

    // when
    dragonEgg.updateQuality()

    // then
    assertThat(dragonEgg.sellIn).isEqualTo(-1)
    assertThat(dragonEgg.quality).isEqualTo(0)
  }

  @Test
  fun `normal item's quality degrades by 2 once the sell-in date passes`() {
    // given
    val dragonEgg = Item("Dragon Egg", 0, 20)

    // when
    dragonEgg.updateQuality()

    // then
    assertThat(dragonEgg.sellIn).isEqualTo(-1)
    assertThat(dragonEgg.quality).isEqualTo(18)
  }

  @Test
  fun `normal item's quality should not be less than 0 when it degrades by 2 after the sell-in date`() {
    // given
    val dragonEgg = Item("Dragon Egg", 0, 1)

    // when
    dragonEgg.updateQuality()

    // then
    assertThat(dragonEgg.sellIn).isEqualTo(-1)
    assertThat(dragonEgg.quality).isEqualTo(0)
  }

  @Test
  fun `aged brie's quality increases by 1`() {
    // given
    val agedBrie = AgedBrie(10, 10)

    // when
    agedBrie.updateQuality()

    // then
    assertThat(agedBrie.sellIn).isEqualTo(9)
    assertThat(agedBrie.quality).isEqualTo(11)
  }

  @Test
  fun `aged brie's quality increases by 2 beyond the sell-in date`() {
    // given
    val agedBrie = AgedBrie(0, 20)

    // when
    agedBrie.updateQuality()

    // then
    assertThat(agedBrie.sellIn).isEqualTo(-1)
    assertThat(agedBrie.quality).isEqualTo(22)
  }

  @Test
  fun `aged brie's quality increase even if it is 0`() {
    // given
    val agedBrie = AgedBrie(10, 0)

    // when
    agedBrie.updateQuality()

    // then
    assertThat(agedBrie.sellIn).isEqualTo(9)
    assertThat(agedBrie.quality).isEqualTo(1)
  }

  @Test
  fun `aged brie's quality does not increase beyond max quality before the sell-in date`() {
    // given
    val agedBrie = AgedBrie(10, MAX_QUALITY)

    // when
    agedBrie.updateQuality()

    // then
    assertThat(agedBrie.sellIn).isEqualTo(9)
    assertThat(agedBrie.quality).isEqualTo(MAX_QUALITY)
  }

  @Test
  fun `aged brie's quality does not increase beyond max quality after the sell-in date`() {
    // given
    val agedBrie = AgedBrie(-5, 49)

    // when
    agedBrie.updateQuality()

    // then
    assertThat(agedBrie.sellIn).isEqualTo(-6)
    assertThat(agedBrie.quality).isEqualTo(MAX_QUALITY)
  }

  @Test
  fun `aged brie's quality is never negative`() {
    // given
    val agedBrie = AgedBrie(0, 0)

    // when
    agedBrie.updateQuality()

    // then
    assertThat(agedBrie.sellIn).isEqualTo(-1)
    assertThat(agedBrie.quality).isEqualTo(2)
  }

  @Test
  fun `sulfuras, never has to be sold or decreases in quality`() {
    // given
    val sulfuras = Sulfuras()
    val originalSellIn = sulfuras.sellIn
    val originalQuality = sulfuras.quality

    // when
    for (i in 100 downTo 0) {
      sulfuras.updateQuality()
    }

    // then
    assertThat(sulfuras.sellIn).isEqualTo(originalSellIn)
    assertThat(sulfuras.quality).isEqualTo(originalQuality)
  }

  @Test
  fun `backstage passes, quality increases by 1 when there are more than 10 days`() {
    // given
    val backstagePasses = BackstagePasses(15, 20)

    // when
    backstagePasses.updateQuality()

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(14)
    assertThat(backstagePasses.quality).isEqualTo(21)
  }

  @Test
  fun `backstage passes, quality increases before the concert event if it is already 0`() {
    // given
    val backstagePasses = BackstagePasses(11, 0)

    // when
    backstagePasses.updateQuality()

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(10)
    assertThat(backstagePasses.quality).isEqualTo(1)
  }

  @Test
  fun `backstage passes, quality increases by 2 between 10 to 6 days`() {
    // given
    val backstagePasses = BackstagePasses(10, 1)

    // when
    for (i in 10 downTo 6) {
      backstagePasses.updateQuality()
    }

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(5)
    assertThat(backstagePasses.quality).isEqualTo(11)
  }

  @Test
  fun `backstage passes, quality increases by 3 between 5 to 1 days`() {
    // given
    val backstagePasses = BackstagePasses(5, 1)

    // when
    for (i in 5 downTo 1) {
      backstagePasses.updateQuality()
    }

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(0)
    assertThat(backstagePasses.quality).isEqualTo(16)
  }


  @Test
  fun `backstage passes, quality does not increase beyond max quality if sell-in is more than 10 days`() {
    // given
    val backstagePasses = BackstagePasses(24, MAX_QUALITY)

    // when
    backstagePasses.updateQuality()

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(23)
    assertThat(backstagePasses.quality).isEqualTo(MAX_QUALITY)
  }

  @Test
  fun `backstage passes, quality does not increase beyond max quality between 10 to 6 days`() {
    // given
    val backstagePasses = BackstagePasses(10, MAX_QUALITY)

    // when
    backstagePasses.updateQuality()

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(9)
    assertThat(backstagePasses.quality).isEqualTo(MAX_QUALITY)
  }

  @Test
  fun `backstage passes, quality does not increase beyond max quality between 5 to 1 days`() {
    // given
    val backstagePasses = BackstagePasses(4, MAX_QUALITY)

    // when
    backstagePasses.updateQuality()

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(3)
    assertThat(backstagePasses.quality).isEqualTo(MAX_QUALITY)
  }

  @Test
  fun `backstage passes, quality falls down to zero after the concert`() {
    // given
    val backstagePasses = BackstagePasses(0, MAX_QUALITY)

    // when
    backstagePasses.updateQuality()

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(-1)
    assertThat(backstagePasses.quality).isEqualTo(0)
  }

  @Test
  fun `backstage passes, quality does not degrade below 0 after the concert`() {
    // given
    val backstagePasses = BackstagePasses(2, MAX_QUALITY)

    // when
    for (i in 2 downTo -3) {
      backstagePasses.updateQuality()
    }

    // then
    assertThat(backstagePasses.sellIn).isEqualTo(-4)
    assertThat(backstagePasses.quality).isEqualTo(0)
  }

  @Test
  fun `conjured mana cake, degrades in quality by 2 before sell-in date`() {
    // given
    val manaCake = ConjuredManaCake(10, 12)

    // when
    manaCake.updateQuality()

    // then
    assertThat(manaCake.sellIn).isEqualTo(9)
    assertThat(manaCake.quality).isEqualTo(10)
  }

  @Test
  fun `conjured mana cake, degrades in quality by 4 after the sell-in date`() {
    // given
    val manaCake = ConjuredManaCake(0, 10)

    // when
    manaCake.updateQuality()

    // then
    assertThat(manaCake.sellIn).isEqualTo(-1)
    assertThat(manaCake.quality).isEqualTo(6)
  }

  @Test
  fun `conjured mana cake, quality does not go below 0`() {
    // given
    val manaCake = ConjuredManaCake(0, 0)

    // when
    manaCake.updateQuality()

    // then
    assertThat(manaCake.sellIn).isEqualTo(-1)
    assertThat(manaCake.quality).isEqualTo(0)
  }
}
