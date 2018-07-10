package org.xflash.lwjgl.azul.model;

import java.util.List;

@FunctionalInterface
public interface TileNotifier {
    void listChange(List<Tile> tiles);
}
