package org.xflash.lwjgl.azul.model;

import org.newdawn.slick.Color;
import org.xflash.lwjgl.azul.observer.BeanWrapper;
import org.xflash.lwjgl.azul.observer.Observable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Fabrick represent a source of Tiles
 */
public class Fabrick {
    private static final int NB_PICK = 4;
    private final TileSet tileSet;
    private final DropZone dropZone;

    private BeanWrapper<List<Tile>> tiles = new BeanWrapper<>(Collections.emptyList());

    public Fabrick(TileSet tileSet, DropZone dropZone) {
        this.tileSet = tileSet;
        this.dropZone = dropZone;
    }

    public void pick() {
        pick(NB_PICK);
    }

    private void pick(int nbPick) {
        List<Tile> topick = new ArrayList<>();
        for (int i = 0; i < nbPick; i++) {
            topick.add(tileSet.pick());
        }
        tiles.set(topick);
    }


    public void playerPick(Player currentPlayer, final Color color) {
        List<Tile> playerTiles = currentPlayer.getTiles().get();
        List<Tile> dropZoneTiles = dropZone.getTiles().get();
        for (Tile tile : this.tiles.get()) {
            if (tile.getColor().equals(color))
                playerTiles.add(tile);
            else
                dropZoneTiles.add(tile);
        }
        tiles.set(Collections.emptyList());
        dropZone.getTiles().set(dropZoneTiles);
        currentPlayer.getTiles().set(playerTiles);
    }

    public Observable<List<Tile>> getTiles() {
        return tiles;
    }
}
