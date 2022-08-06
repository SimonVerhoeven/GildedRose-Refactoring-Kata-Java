package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private final Item[] items;

    public GildedRose(Item... items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.asList(items).forEach(item -> {
            if (!item.name.equals(AGED_BRIE)
                && !item.name.equals(BACKSTAGE_PASSES)) {
                if (item.quality > 0) {
                    if (!item.name.equals(SULFURAS)) {
                        adjustQuality(item, - 1);
                    }
                }
            } else {
                if (item.quality < 50) {
                    adjustQuality(item, 1);

                    if (item.name.equals(BACKSTAGE_PASSES)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                adjustQuality(item, 1);
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                adjustQuality(item, 1);
                            }
                        }
                    }
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(BACKSTAGE_PASSES)) {
                        if (item.quality > 0) {
                            if (!item.name.equals(SULFURAS)) {
                                adjustQuality(item, -1);
                            }
                        }
                    } else {
                        adjustQuality(item, -item.quality);

                    }
                } else {
                    if (item.quality < 50) {
                        adjustQuality(item, 1);
                    }
                }
            }
        });
    }

    private void adjustQuality(Item item, int amount) {
        item.quality = item.quality + amount;
    }

    public Item[] getItems() {
        return items;
    }
}
