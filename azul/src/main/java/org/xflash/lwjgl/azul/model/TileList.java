package org.xflash.lwjgl.azul.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TileList implements Iterable<Tile> {
    private final List<Notifier<List<Tile>>> notifiers = new ArrayList<>();
    public List<Tile> tiles = new ArrayList<>();


    public void add(Tile tile) {
        tiles.add(tile);
    }

    public Tile get(int idx) {
        return tiles.get(idx);
    }

    @Override
    public Iterator<Tile> iterator() {
        return tiles.listIterator();
    }

    public void clear() {
        tiles.clear();
        notifyListeners();
    }

    public void register(Notifier<List<Tile>> notifier) {
        notifiers.add(notifier);
    }

    public void unregister(Notifier<List<Tile>> notifier) {
        notifiers.remove(notifier);
    }

    public void notifyListeners() {
        for (Notifier<List<Tile>> notifier : notifiers) {
            notifier.onChange(tiles);
        }
    }

    public int size() {
        return tiles.size();
    }
}
