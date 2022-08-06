package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.ItemTestHelper.verifyItemStatistics;

public class ConjuredItemTest {

    @Test
    public void updateQuality() {
        GildedRose gildedRose = new GildedRose(createItem(7, 10));
        gildedRose.updateQuality();
        verifyItemStatistics(createItem(6, 8), gildedRose.getItems()[0]);
    }

    @Test
    public void updateQuality_doubleQualityDecreaseWhenExpired() {
        GildedRose gildedRose = new GildedRose(createItem(0, 10));
        gildedRose.updateQuality();
        verifyItemStatistics(createItem(-1, 6), gildedRose.getItems()[0]);
    }

    @Test
    public void updateQuality_minItemQuality() {
        GildedRose gildedRose = new GildedRose(createItem(7, 0));
        gildedRose.updateQuality();
        verifyItemStatistics(createItem(6, 0), gildedRose.getItems()[0]);
    }

    @Test
    public void updateQuality_minItemQuality_Uneven() {
        GildedRose gildedRose = new GildedRose(createItem(7, 1));
        gildedRose.updateQuality();
        verifyItemStatistics(createItem(6, 0), gildedRose.getItems()[0]);
    }

    private Item createItem(int sellIn, int quality) {
        return new Item("Conjured water", sellIn, quality);
    }
}
