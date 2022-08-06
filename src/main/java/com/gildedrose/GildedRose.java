package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final int MIN_ITEM_QUALITY = 0;
    private static final int MAX_COMMON_ITEM_QUALITY = 50;
    private final Item[] items;

    public GildedRose(Item... items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.asList(items).forEach(item -> {
            if (!item.name.equals(AGED_BRIE)  && !item.name.equals(BACKSTAGE_PASSES)) {
                if (!item.name.equals(SULFURAS)) {
                    adjustQuality(item, - 1);
                }
            } else {
                adjustQuality(item, 1);

                if (item.name.equals(BACKSTAGE_PASSES)) {
                    if (item.sellIn < 11) {
                        adjustQuality(item, 1);
                    }

                    if (item.sellIn < 6) {
                        adjustQuality(item, 1);
                    }
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < MIN_ITEM_QUALITY) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(BACKSTAGE_PASSES)) {
                        if (!item.name.equals(SULFURAS)) {
                            adjustQuality(item, -1);
                        }
                    } else {
                        adjustQuality(item, -item.quality);

                    }
                } else {
                    adjustQuality(item, 1);
                }
            }
        });
    }

    private void adjustQuality(Item item, int amount) {
        final int adjustedQuality = item.quality + amount;
        if (adjustedQuality >= MIN_ITEM_QUALITY && adjustedQuality <= MAX_COMMON_ITEM_QUALITY) {
            item.quality = adjustedQuality;
        }
    }

    public Item[] getItems() {
        return items;
    }
}
