package org.xflash.lwjgl.azul.model;

import org.xflash.lwjgl.azul.observer.BeanWrapper;
import org.xflash.lwjgl.azul.observer.Observable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private final int num;

    private BeanWrapper<List<Tile>> tiles = new BeanWrapper<>(Collections.emptyList());

    public Player(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Player{" + getNum() + '}';
    }

    public int getTilesNb() {
        return tiles.get().size();
    }

    public Tile getTileToPlay(int i) {
        return tiles.get().get(i);
    }

    public BeanWrapper<List<Tile>> getTiles() {
        return tiles;
    }
}
