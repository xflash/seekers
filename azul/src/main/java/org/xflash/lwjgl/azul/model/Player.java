package org.xflash.lwjgl.azul.model;

import org.xflash.lwjgl.azul.states.elements.PlayerPicker;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int num;

    private final TileList tileList = new TileList();

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


    public void add(Tile tile) {
        tileList.add(tile);
    }

    public void notifyListeners() {
        tileList.notifyListeners();
    }

    public Tile getTileToPlay(int i) {
        return tileList.get(i);
    }

    public int getTilesToPlay() {
        return tileList.size();
    }

    public void register(TileNotifier tileNotifier) {
        tileList.register(tileNotifier);
    }

    public void unregister(TileNotifier tileNotifier) {
        tileList.unregister(tileNotifier);
    }
}
