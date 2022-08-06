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
            final boolean willExpire = item.sellIn < 1;
            final int degradeAmount = determineDegradation(item, willExpire);
            final boolean itemDegrades = !Arrays.asList(AGED_BRIE, BACKSTAGE_PASSES, SULFURAS).contains(item.name);

            if (itemDegrades) {
                adjustQuality(item, degradeAmount);
            }

            if (AGED_BRIE.equals(item.name)){
                adjustQuality(item, 1);
                if (willExpire) {
                    adjustQuality(item, 1);
                }
            }

            if (BACKSTAGE_PASSES.equals(item.name)) {
                adjustQuality(item, 1);

                if (item.sellIn < 11) {
                    adjustQuality(item, 1);
                }

                if (item.sellIn < 6) {
                    adjustQuality(item, 1);
                }
                if (willExpire) {
                    adjustQuality(item, -item.quality);
                }
            }

            if (!SULFURAS.equals(item.name)) {
                item.sellIn = item.sellIn - 1;
            }
        });
    }

    private int determineDegradation(Item item, boolean willExpire) {
        final int baseDegradation = item.name.startsWith(CONJURED_ITEM_PREFIX) ? -2 : -1;
        return willExpire ? 2 * baseDegradation : baseDegradation;
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
