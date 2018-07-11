package org.xflash.lwjgl.azul.model;

import org.newdawn.slick.Color;

import java.beans.PropertyChangeSupport;
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
    private final PropertyChangeSupport support;
    private Vector<Tile> tiles = new Vector<>();

    public TileSet() {
        support = new PropertyChangeSupport(this);
        reset();
    }

    public void reset() {
        Vector<Tile> vector = new Vector<>();
        for (Color color : colors) {
            for (int i = 0; i < NB; i++) {
                vector.add(new Tile(color));
            }
        }

        Collections.shuffle(vector);
        support.firePropertyChange("tiles", this.tiles, vector);
        this.tiles = vector;
    }

    public int remaining() {
        return tiles.size();
    }

    public Tile pick() {
        return tiles.remove(0);
    }


}
