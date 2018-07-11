package org.xflash.lwjgl.azul.model;

import org.newdawn.slick.Color;

import java.util.Collections;
import java.util.Vector;

/**
 * 100 tiles of 5 colors each made of 20 tiles
 */
public class TileSet {
    private static final int NB = 20;
    private static final Color[] colors = new Color[]{
            Color.blue, Color.yellow, Color.red, Color.green, Color.cyan
    };

    private final Vector<Tile> tiles = new Vector<>();

    public TileSet() {
        reset();
    }

    public void reset() {
        for (Color color : colors) {
            for (int i = 0; i < NB; i++) {
                tiles.add(new Tile(color));
            }
        }

        Collections.shuffle(tiles);
    }

    public int remaining() {
        return tiles.size();
    }

    public Tile pick() {
        return tiles.remove(0);
    }


}
