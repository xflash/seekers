package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.model.Tile;
import org.xflash.lwjgl.azul.states.dispatcher.RadialDispatcher;
import org.xflash.lwjgl.azul.ui.MouseOverShape;

import java.beans.PropertyChangeEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DropzonePicker  {

    private final GUIContext guiContext;
    private final int x;
    private final int y;
    private Set<MouseOverShape> subTiles = new HashSet<>();

    public DropzonePicker(GUIContext guiContext, int x, int y) {
        this.guiContext = guiContext;
        this.x = x;
        this.y = y;
    }

    public void render(GUIContext guiContext, Graphics g) {
        for (MouseOverShape subTile : subTiles) {
            subTile.render(guiContext, g);
        }
    }

    public void onTilesChange(PropertyChangeEvent evt) {
        System.out.println("Dropzon onChange = "+evt);
        List<Tile> updated = (List<Tile>) evt.getNewValue();
        subTiles.clear();
        if(updated.size()>0) {
            new RadialDispatcher(updated.size(), 10)
                    .dispatch((x2, y2) -> {
                        Tile tile = updated.get(subTiles.size());
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
