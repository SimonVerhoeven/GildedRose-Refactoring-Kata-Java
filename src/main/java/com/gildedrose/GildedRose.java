package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED_ITEM_PREFIX = "Conjured ";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final int MIN_ITEM_QUALITY = 0;
    private static final int MAX_COMMON_ITEM_QUALITY = 50;
    private final Item[] items;

    public GildedRose(Item... items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.asList(items).forEach(item -> {
            final int degradeAmount = determineDegradation(item);
            final boolean itemDegrades = !Arrays.asList(AGED_BRIE, BACKSTAGE_PASSES, SULFURAS).contains(item.name);

            if (itemDegrades) {
                adjustQuality(item, degradeAmount);
            }

            if (AGED_BRIE.equals(item.name)){
                adjustQuality(item, 1);
            }

            if (BACKSTAGE_PASSES.equals(item.name)) {
                adjustQuality(item, 1);

                if (item.sellIn < 11) {
                    adjustQuality(item, 1);
                }

                if (item.sellIn < 6) {
                    adjustQuality(item, 1);
                }
            }

            if (!SULFURAS.equals(item.name)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!AGED_BRIE.equals(item.name)) {
                    if (!BACKSTAGE_PASSES.equals(item.name)) {
                        if (!SULFURAS.equals(item.name)) {
                            adjustQuality(item, degradeAmount);
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

    private int determineDegradation(Item item) {
        return item.name.startsWith(CONJURED_ITEM_PREFIX) ? -2 : -1;
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
