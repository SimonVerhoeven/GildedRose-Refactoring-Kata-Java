package com.gildedrose;

import org.junit.jupiter.api.Assertions;

public abstract class ItemTestHelper {
    public static void verifyItemStatistics(Item expected, Item actual) {
        Assertions.assertEquals(expected.quality, actual.quality);
        Assertions.assertEquals(expected.sellIn, actual.sellIn);
        Assertions.assertEquals(expected.name, actual.name);
    }
}
