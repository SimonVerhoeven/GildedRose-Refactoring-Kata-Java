package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.ItemTestHelper.verifyItemStatistics;

public class CommonItemTest {

    @Test
    public void updateQuality() {
        GildedRose gildedRose = new GildedRose(createItem(7, 10));
        gildedRose.updateQuality();
        verifyItemStatistics(createItem(6, 9), gildedRose.getItems()[0]);
    }

    @Test
    public void updateQuality_doubleQualityDecreaseWhenExpired() {
        GildedRose gildedRose = new GildedRose(createItem(0, 10));
        gildedRose.updateQuality();
        verifyItemStatistics(createItem(-1, 8), gildedRose.getItems()[0]);
    }

    @Test
    public void updateQuality_minItemQuality() {
        GildedRose gildedRose = new GildedRose(createItem(7, 0));
        gildedRose.updateQuality();
        verifyItemStatistics(createItem(6, 0), gildedRose.getItems()[0]);
    }

    private Item createItem(int sellIn, int quality) {
        return new Item("Boar pelt", sellIn, quality);
    }
}
