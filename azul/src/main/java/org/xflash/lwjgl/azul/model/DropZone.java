package org.xflash.lwjgl.azul.model;

import org.xflash.lwjgl.azul.states.elements.DropzonePicker;
import org.xflash.lwjgl.azul.states.elements.PlayerPicker;

import java.util.ArrayList;
import java.util.List;

public class DropZone {

    private final TileList tileList = new TileList();

    public void add(Tile tile) {
        tileList.add(tile);
    }

    public void notifyListeners() {
        tileList.notifyListeners();
    }

    public void register(TileNotifier tileNotifier) {
        tileList.register(tileNotifier);
    }

    public void unregister(TileNotifier tileNotifier) {
        tileList.unregister(tileNotifier);
    }
}
