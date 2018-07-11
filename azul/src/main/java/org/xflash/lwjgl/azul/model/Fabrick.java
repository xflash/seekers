package org.xflash.lwjgl.azul.model;

import org.newdawn.slick.Color;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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

    private List<Tile> tiles = new ArrayList<>();
    private PropertyChangeSupport support;

    public Fabrick(TileSet tileSet, DropZone dropZone) {
        this.support = new PropertyChangeSupport(this);
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
        setTiles(topick);
    }

    public void setTiles(List<Tile> tiles) {
        support.firePropertyChange("tiles", this.tiles, tiles);
        this.tiles = tiles;
    }

    public void playerPick(Player currentPlayer, final Color color) {
        List<Tile> playerTiles = new ArrayList<>(currentPlayer.getTiles());
        List<Tile> dropZoneTiles = new ArrayList<>(dropZone.getTiles());
        for (Tile tile : this.tiles) {
            if (tile.getColor().equals(color))
                playerTiles.add(tile);
            else
                dropZoneTiles.add(tile);
        }
        setTiles(Collections.emptyList());
        dropZone.setTiles(dropZoneTiles);
        currentPlayer.setTiles(playerTiles);
    }

    public void addTilesObserver(PropertyChangeListener propertyChangeListener) {
        support.addPropertyChangeListener("tiles", propertyChangeListener);
    }
}
