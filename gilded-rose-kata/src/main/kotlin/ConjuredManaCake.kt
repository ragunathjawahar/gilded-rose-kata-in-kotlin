class ConjuredManaCake(
    sellIn: Int,
    quality: Int
) : Item("Conjured Mana Cake", sellIn, quality, ConjuredQualityUpdater())
