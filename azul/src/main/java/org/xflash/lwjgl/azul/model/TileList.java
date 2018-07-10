package org.xflash.lwjgl.azul.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TileList  implements Iterable<Tile> {
    private final ArrayList<TileNotifier> notifiers = new ArrayList<>();
    private List<Tile> tiles = new ArrayList<>();

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
    }

    public void register(TileNotifier notifier) {
        notifiers.add(notifier);
    }

    public void unregister(TileNotifier notifier) {
        notifiers.remove(notifier);
    }

    public void notifyListeners() {
        for (TileNotifier notifier : notifiers) {
            notifier.listChange(tiles);
        }
    }

    public int size() {
        return tiles.size();
    }
}
