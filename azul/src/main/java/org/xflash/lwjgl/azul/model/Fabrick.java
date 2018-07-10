package org.xflash.lwjgl.azul.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A Fabrick represent a source of Tiles
 */
public class Fabrick {
    private static final int NB_PICK = 4;
    private final TileSet tileSet;
    private List<Tile> tiles = new ArrayList<>();

    public Fabrick(TileSet tileSet) {
        this.tileSet = tileSet;
    }

    public void pick() {

        for (int i = 0; i < NB_PICK; i++) {
            tiles.add(tileSet.pick());
        }
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
