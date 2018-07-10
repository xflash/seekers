package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.states.dispatcher.RadialDispatcher;

import java.util.ArrayList;
import java.util.List;

public class FabricksPicker {
    private final List<MouseOverShape> cells = new ArrayList<>();
    private final int radius = 60;

    public FabricksPicker(GUIContext guiContext, int nb, int x0, int y0, ComponentListener componentListener) {
        new RadialDispatcher(nb, radius)
                .dispatch((x1, y1) -> {
                    MouseOverShape mouseOverShape = new MouseOverShape(guiContext, x0+x1, y0+y1) {
                                        @Override
                                        public Shape createShapeAt(int x, int y) {
                                            return new Circle(x, y, radius/3);
                                        }
                                    };
                    mouseOverShape.addListener(componentListener);
                    cells.add(mouseOverShape);
                });

    }

    public void render(GUIContext guiContext, Graphics g) throws SlickException {
        for (MouseOverShape mouseOverShape : cells) {
            mouseOverShape.render(guiContext, g);
        }
    }
}
