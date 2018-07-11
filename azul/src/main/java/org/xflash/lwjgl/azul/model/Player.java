package org.xflash.lwjgl.azul.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private final int num;
    private final PropertyChangeSupport support;

    private List<Tile> tiles = new ArrayList<>();

    public Player(int num) {
        this.num = num;
        this.support = new PropertyChangeSupport(this);
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Player{" + getNum() + '}';
    }

    public int getTilesNb() {
        return tiles.size();
    }

    public Tile getTileToPlay(int i) {
        return tiles.get(i);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        support.firePropertyChange("tiles", this.tiles, tiles);
        this.tiles = tiles;
    }

    public void removeTilesObservers() {
        Arrays.stream(support.getPropertyChangeListeners("tiles")).forEach(support::removePropertyChangeListener);
    }

    public void addTilesObserver(PropertyChangeListener propertyChangeListener) {
        support.addPropertyChangeListener("tiles", propertyChangeListener);
    }
}
