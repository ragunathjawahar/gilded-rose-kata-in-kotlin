class AgedBrie(
    sellIn: Int,
    quality: Int
) : Item("Aged Brie" ,sellIn, quality, AgedBrieQualityUpdater())
