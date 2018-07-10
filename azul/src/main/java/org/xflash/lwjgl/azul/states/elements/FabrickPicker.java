package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.model.Fabrick;
import org.xflash.lwjgl.azul.model.Tile;
import org.xflash.lwjgl.azul.states.dispatcher.RadialDispatcher;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class FabrickPicker {

    private final MouseOverShape mouseOverShape;
    private final Fabrick fabrick;
//    private Set<MouseOverShape> subTiles = new TreeSet<>(Comparator.comparingInt(o -> o.getColor().hashCode()));
    private Set<MouseOverShape> subTiles = new HashSet<>();

    public FabrickPicker(GUIContext guiContext, Fabrick fabrick, float x, float y, float radius) {
        this.fabrick = fabrick;
        mouseOverShape = new MouseOverShape(guiContext, x, y) {
            @Override
            public Shape createShapeAt(int x, int y) {
                return new Circle(x, y, radius);
            }
        };

        new RadialDispatcher(fabrick.getTiles().size(), radius*.55f)
                .dispatch((x2,y2)->{
                    Tile tile = fabrick.getTiles().get(subTiles.size());
                    MouseOverShape mouseOverShape = new MouseOverShape(guiContext, x+x2, y+y2) {
                        @Override
                        public Shape createShapeAt(int x, int y) {
                            return new Rectangle(x-4, y-4, 8, 8);
                        }
                    };
                    mouseOverShape.setColor(tile.getColor());
                    mouseOverShape.setOverColor(tile.getColor().darker(.6f));
                    mouseOverShape.setDownColor(tile.getColor().darker(.6f));
                    subTiles.add(mouseOverShape);
                    mouseOverShape.addListener((source -> {
                        System.out.println("tile = " + tile);
                    }));

                });

    }


    public void render(GUIContext guiContext, Graphics g) {
        mouseOverShape.render(guiContext, g);
        for (MouseOverShape subTile : subTiles) {
            subTile.render(guiContext, g);
        }
    }
}
