package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.ItemTestHelper.verifyItemStatistics;

public class AgedBrieTest {

    @Test
    public void updateQuality() {
        GildedRose gildedRose = new GildedRose(createAgedBrie(7, 1));
        gildedRose.updateQuality();
        verifyItemStatistics(createAgedBrie(6, 2), gildedRose.getItems()[0]);
    }

    @Test
    public void updateQuality_doubleQualityIncreaseWhenExpired() {
        GildedRose gildedRose = new GildedRose(createAgedBrie(0, 10));
        gildedRose.updateQuality();
        verifyItemStatistics(createAgedBrie(-1, 12), gildedRose.getItems()[0]);
    }

    @Test
    public void updateQuality_maxItemQuality() {
        GildedRose gildedRose = new GildedRose(createAgedBrie(7, 50));
        gildedRose.updateQuality();
        verifyItemStatistics(createAgedBrie(6, 50), gildedRose.getItems()[0]);
    }

    private Item createAgedBrie(int sellIn, int quality) {
        return new Item("Aged Brie", sellIn, quality);
    }
}
