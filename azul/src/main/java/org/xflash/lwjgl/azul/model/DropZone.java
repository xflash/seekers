package org.xflash.lwjgl.azul.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DropZone {

    private final PropertyChangeSupport support;

    private List<Tile> tiles = new ArrayList<>();

    public DropZone() {
        support = new PropertyChangeSupport(this);
    }

    public void clean() {
        setTiles(Collections.emptyList());
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        support.firePropertyChange("tiles", this.tiles, tiles);
        this.tiles = tiles;
    }

    public void cleanObservers() {
        Arrays.stream(support.getPropertyChangeListeners()).forEach(support::removePropertyChangeListener);
    }

    public void addTilesObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener("tiles", listener);
    }
}
