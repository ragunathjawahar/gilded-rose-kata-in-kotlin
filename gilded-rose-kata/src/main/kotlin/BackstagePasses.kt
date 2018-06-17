class BackstagePasses(
    sellIn: Int,
    quality: Int
) : Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality, BackstagePassesQualityUpdater())
