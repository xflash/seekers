package org.xflash.lwjgl.azul.model;

import org.xflash.lwjgl.azul.observer.BeanWrapper;

import java.util.Collections;
import java.util.List;

public class DropZone {

    private BeanWrapper<List<Tile>> tiles = new BeanWrapper<>(Collections.emptyList());

    public void clean() {
        tiles.set(Collections.emptyList());
    }

    public BeanWrapper<List<Tile>> getTiles() {
        return tiles;
    }
}
