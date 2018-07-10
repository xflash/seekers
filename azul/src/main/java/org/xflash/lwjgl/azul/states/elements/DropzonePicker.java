package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.model.DropZone;
import org.xflash.lwjgl.azul.model.Tile;
import org.xflash.lwjgl.azul.model.TileNotifier;
import org.xflash.lwjgl.azul.states.dispatcher.RadialDispatcher;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DropzonePicker implements TileNotifier {

    private final GUIContext guiContext;
    private final int x;
    private final int y;
    private Set<MouseOverShape> subTiles = new HashSet<>();

    public DropzonePicker(GUIContext guiContext, DropZone dropZone, int x, int y, ComponentListener componentListener) {
        this.guiContext = guiContext;
        this.x = x;
        this.y = y;
        dropZone.register(this);
    }


    public void render(GUIContext guiContext, Graphics g) {
        for (MouseOverShape subTile : subTiles) {
            subTile.render(guiContext, g);
        }
    }

    @Override
    public void listChange(List<Tile> tiles) {
        System.out.println("Dropzon listChange = ");
        subTiles.clear();
        if(tiles.size()>0) {
            new RadialDispatcher(tiles.size(), 10)
                    .dispatch((x2, y2) -> {
                        Tile tile = tiles.get(subTiles.size());
                        MouseOverShape mouseOverShape = new MouseOverShape(guiContext, x + x2, y + y2) {
                            @Override
                            public Shape createShapeAt(int x, int y) {
                                return new Rectangle(x - 4, y - 4, 8, 8);
                            }
                        };
                        Color tileColor = tile.getColor();
                        mouseOverShape.setColor(tileColor);
                        mouseOverShape.setOverColor(tileColor.darker(.6f));
                        mouseOverShape.setDownColor(tileColor.darker(.6f));
                        subTiles.add(mouseOverShape);
                        mouseOverShape.addListener((source -> {
                            System.out.println("tile = " + tile);
//                            fabrick.playerPick(currentPlayer, tileColor);
                        }));

                    });
        }

    }
}
