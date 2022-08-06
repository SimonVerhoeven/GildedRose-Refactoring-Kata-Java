package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.ItemTestHelper.verifyItemStatistics;

public class SulfurasTest {

    @Test
    public void updateQuality() {
        GildedRose gildedRose = new GildedRose(createSulfuras(7, 1));
        gildedRose.updateQuality();
        verifyItemStatistics(createSulfuras(7, 1), gildedRose.getItems()[0]);
    }

    @Test
    public void updateQuality_expired() {
        GildedRose gildedRose = new GildedRose(createSulfuras(0, 10));
        gildedRose.updateQuality();
        verifyItemStatistics(createSulfuras(0, 10), gildedRose.getItems()[0]);
    }

    private Item createSulfuras(int sellIn, int quality) {
        return new Item("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }
}
