package org.xflash.lwjgl.azul.model;

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

    public void register(Notifier<List<Tile>> notifier) {
        tileList.register(notifier);
    }

    public void unregister(Notifier<List<Tile>> notifier) {
        tileList.unregister(notifier);
    }
}
