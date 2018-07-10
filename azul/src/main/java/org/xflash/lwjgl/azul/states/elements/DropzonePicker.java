package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

public class DropzonePicker {

    private final MouseOverShape mouseOverShape;

    public DropzonePicker(GameContainer container, int x, int y, ComponentListener componentListener) {
        mouseOverShape = new MouseOverShape(container, x, y) {
            @Override
            public Shape createShapeAt(int x, int y) {
                return new Rectangle(x - 50 / 2, y - 25 / 2, 50, 25);
            }
        };

        mouseOverShape.addListener(componentListener);
    }


    public void render(GUIContext guiContext, Graphics g) {
        mouseOverShape.render(guiContext, g);
    }
}
