package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.ItemTestHelper.verifyItemStatistics;

public class BackstageTest {

    @Test
    public void updateQuality() {
        GildedRose gildedRose = new GildedRose(createBackstageTicket(20, 1));
        gildedRose.updateQuality();
        verifyItemStatistics(createBackstageTicket(19, 2), gildedRose.getItems()[0]);
    }

    @Test
    public void updateQuality_moreThan5LessThan10() {
        GildedRose gildedRose = new GildedRose(createBackstageTicket(9, 10));
        gildedRose.updateQuality();
        verifyItemStatistics(createBackstageTicket(8, 12), gildedRose.getItems()[0]);
    }

    @Test
    public void updateQuality_5OrLess() {
        GildedRose gildedRose = new GildedRose(createBackstageTicket(5, 10));
        gildedRose.updateQuality();
        verifyItemStatistics(createBackstageTicket(4, 13), gildedRose.getItems()[0]);
    }

    @Test
    public void updateQuality_expired() {
        GildedRose gildedRose = new GildedRose(createBackstageTicket(0, 42));
        gildedRose.updateQuality();
        verifyItemStatistics(createBackstageTicket(-1, 0), gildedRose.getItems()[0]);
    }

    private Item createBackstageTicket(int sellIn, int quality) {
        return new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }
}
