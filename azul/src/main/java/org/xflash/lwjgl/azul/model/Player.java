package org.xflash.lwjgl.azul.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int num;

    private List<Tile> tilesToPlay = new ArrayList<>();

    public Player(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Player{" + num + '}';
    }

    public List<Tile> getTilesToPlay() {
        return tilesToPlay;
    }
}
