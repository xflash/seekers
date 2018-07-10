package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.model.Fabrick;

public class FabrickPicker {

    private final MouseOverShape mouseOverShape;

    public FabrickPicker(GUIContext guiContext, Fabrick fabrick, float x, float y, float radius) {
        mouseOverShape = new MouseOverShape(guiContext, x, y) {
            @Override
            public Shape createShapeAt(int x, int y) {
                return new Circle(x, y, radius);
            }
        };
        mouseOverShape.addListener(source -> {
            System.out.println("fabrick = " + fabrick);
        });

    }


    public void render(GUIContext guiContext, Graphics g) {
        mouseOverShape.render(guiContext, g);
    }
}
