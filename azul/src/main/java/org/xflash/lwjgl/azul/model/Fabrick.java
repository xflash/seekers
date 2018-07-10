package org.xflash.lwjgl.azul.model;

import org.newdawn.slick.Color;

import java.util.List;

/**
 * A Fabrick represent a source of Tiles
 */
public class Fabrick {
    private static final int NB_PICK = 4;
    private final TileSet tileSet;
    private final DropZone dropZone;
    public final TileList tileList = new TileList();

    public Fabrick(TileSet tileSet, DropZone dropZone) {
        this.tileSet = tileSet;
        this.dropZone = dropZone;
    }

    public void pick() {
        pick(NB_PICK);
    }

    public void pick(int nbPick) {
        for (int i = 0; i < nbPick; i++) {
            tileList.add(tileSet.pick());
        }
        notifyListeners();
    }

    public Tile getTile(int idx) {
        return tileList.get(idx);
    }

    public void playerPick(Player currentPlayer, final Color color) {
        for (Tile tile : tileList) {
            if (tile.getColor().equals(color))
                currentPlayer.add(tile);
            else
                dropZone.add(tile);
        }
        tileList.clear();
        currentPlayer.notifyListeners();
        dropZone.notifyListeners();

    }

    public void notifyListeners() {
        tileList.notifyListeners();
    }

    public int getTilesSize() {
        return tileList.size();
    }

    public void register(Notifier<List<Tile>> notifier) {
        tileList.register(notifier);

    }

    public void unregister(Notifier<List<Tile>> notifier) {
        tileList.unregister(notifier);
    }

}
