package org.xflash.lwjgl.azul.model;

import java.util.List;

public class DropZone {

    private final TileList tileList = new TileList();

    public void add(Tile tile) {
        tileList.add(tile);
    }

    public void notifyListeners() {
        tileList.notifyListeners();
    }

    public void register(Notifier<List<Tile>> notifier) {
        tileList.register(notifier);
    }

    public void unregister(Notifier<List<Tile>> notifier) {
        tileList.unregister(notifier);
    }

    public void clean() {
        tileList.clear();

    }
}
