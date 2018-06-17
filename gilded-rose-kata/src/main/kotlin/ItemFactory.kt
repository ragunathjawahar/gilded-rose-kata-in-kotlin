import quality.AgedBrieQualityUpdater
import quality.BackstagePassesQualityUpdater
import quality.ConjuredQualityUpdater
import quality.NoopQualityUpdater
import sellin.NoopSellInUpdater

const val CONJURED_MANA_CAKE = "Conjured Mana Cake"
const val DEXTERITY_VEST = "+5 Dexterity Vest"
const val ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose"

class ItemFactory {
  companion object {
    fun agedBrie(sellIn: Int, quality: Int): Item =
        Item("Aged Brie", sellIn, quality, AgedBrieQualityUpdater())

    fun backstagePasses(sellIn: Int, quality: Int): Item =
        Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality, BackstagePassesQualityUpdater())

    fun conjured(name: String, sellIn: Int, quality: Int): Item =
        Item(name, sellIn, quality, ConjuredQualityUpdater())

    fun normal(name: String, sellIn: Int, quality: Int): Item =
        Item(name, sellIn, quality)

    fun sulfuras(): Item =
        Item("Sulfuras, Hand of Ragnaros", 0, 80, NoopQualityUpdater(), NoopSellInUpdater())
  }
}
