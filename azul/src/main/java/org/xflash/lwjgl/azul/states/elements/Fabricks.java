package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;

import java.util.ArrayList;
import java.util.List;

public class Fabricks {
    private final List<Shape> shapes;
    private int x;
    private int y;

    public Fabricks(GUIContext guiContext, int nb) {
//        super(guiContext);

        shapes = new ArrayList<>();
        new RadialDispatcher(nb, 60)
                .dispatch((x, y) -> shapes.add(new Circle(x, y, 25)));
    }

    public void render(GUIContext guiContext, Graphics g) throws SlickException {
        g.translate(x, y);
        for (Shape shape : shapes) {
            g.draw(shape);
        }
        g.resetTransform();
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
