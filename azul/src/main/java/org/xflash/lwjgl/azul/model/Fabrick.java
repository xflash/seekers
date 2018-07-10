package org.xflash.lwjgl.azul.model;

import org.newdawn.slick.Color;
import org.xflash.lwjgl.azul.states.elements.FabrickPicker;
import org.xflash.lwjgl.azul.states.elements.FabricksPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * A Fabrick represent a source of Tiles
 */
public class Fabrick {
    private static final int NB_PICK = 4;
    private final TileSet tileSet;
    private final DropZone dropZone;
    private final TileList tileList = new TileList();

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
        notifyListeners();
        currentPlayer.notifyListeners();
        dropZone.notifyListeners();

    }

    private void notifyListeners() {
        tileList.notifyListeners();
    }

    public int getTilesSize() {
        return tileList.size();
    }

    public void register(TileNotifier tileNotifier) {
        tileList.register(tileNotifier);

    }

    public void unregister(TileNotifier tileNotifier) {
        tileList.unregister(tileNotifier);
    }

}
