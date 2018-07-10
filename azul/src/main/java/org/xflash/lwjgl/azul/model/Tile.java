package org.xflash.lwjgl.azul.model;

import org.newdawn.slick.Color;

public class Tile {
    private final Color color;

    public Tile(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
