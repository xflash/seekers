package org.xflash.lwjgl.azul.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractTilesContainer implements TilesContainer {

    private final PropertyChangeSupport support;
    private List<Tile> tiles ;

    AbstractTilesContainer() {
        this.support = new PropertyChangeSupport(this);
    }

    @Override
    public void addTilesObserver(PropertyChangeListener propertyChangeListener) {
        support.addPropertyChangeListener("tiles", propertyChangeListener);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        support.firePropertyChange("tiles", this.tiles, tiles);
        this.tiles=tiles;
    }

    public void reset() {
        setTiles(Collections.emptyList());
    }
}
