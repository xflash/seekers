package org.xflash.lwjgl.azul.model;

import org.junit.Assert;
import org.junit.Test;

public class TileSetTest {

    @Test
    public void checkPicker() throws Exception {

        TileSet tileSet = new TileSet();
        Assert.assertEquals("remaining", 100, tileSet.remaining());
        for (int i = 0; i < 100; i++) {
            tileSet.pick();
        }
        Assert.assertEquals("remaining", 0, tileSet.remaining());

    }
}