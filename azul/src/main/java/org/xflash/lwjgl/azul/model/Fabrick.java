package org.xflash.lwjgl.azul.model;

import java.util.HashSet;

/**
 * A Fabrick represent a source of Tiles
 */
public class Fabrick {
    private static final int NB_PICK = 4;
    private final TileSet tileSet;
    private HashSet<Tile> tiles = new HashSet<>();

    public Fabrick(TileSet tileSet) {
        this.tileSet = tileSet;
    }

    public void pick() {

        for (int i = 0; i < NB_PICK; i++) {
            tiles.add(tileSet.pick());
        }
    }
}
