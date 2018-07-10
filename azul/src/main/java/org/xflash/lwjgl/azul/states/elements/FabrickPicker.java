package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.model.Fabrick;
import org.xflash.lwjgl.azul.model.Notifier;
import org.xflash.lwjgl.azul.model.Player;
import org.xflash.lwjgl.azul.model.Tile;
import org.xflash.lwjgl.azul.states.dispatcher.RadialDispatcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FabrickPicker implements Notifier<List<Tile>> {

    private final MouseOverShape mouseOverShape;
    private final GUIContext guiContext;
    private final Fabrick fabrick;
    private final float x;
    private final float y;
    private final float radius;
    private Set<MouseOverShape> subTiles = new HashSet<>();
    private Player currentPlayer;

    FabrickPicker(GUIContext guiContext, Fabrick fabrick, float x, float y, float radius) {
        this.guiContext = guiContext;
        this.fabrick = fabrick;
        this.x = x;
        this.y = y;
        this.radius = radius;
        mouseOverShape = new MouseOverShape(guiContext, x, y) {
            @Override
            public Shape createShapeAt(int x, int y) {
                return new Circle(x, y, radius);
            }
        };
    }

    @Override
    public void onChange(List<Tile> updated) {
        subTiles.clear();
        int sz = updated.size();
        if (sz > 0) {
            new RadialDispatcher(sz, radius * .55f)
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
                            fabrick.playerPick(currentPlayer, tileColor);
                        }));
                    });
        }
    }


    public void render(GUIContext guiContext, Graphics g) {
        mouseOverShape.render(guiContext, g);
        for (MouseOverShape subTile : new ArrayList<>(subTiles)) {
            subTile.render(guiContext, g);
        }

    }

    void onChange(Player player) {
        this.currentPlayer = player;
    }

    void unregisterFromFabrick() {
        fabrick.unregister(this);
    }
}
