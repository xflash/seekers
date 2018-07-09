package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.util.InputAdapter;

public class Fabrick extends InputAdapter {

    private final Shape shape;

    public Fabrick(GUIContext guiContext, float x, float y, float radius) {
        shape = new Circle(x, y, radius);

//        guiContext.getInput().addMouseListener(moa);
//        moa.addListener(source -> System.out.println("source = " + source));


    }

    public Shape getShape() {
        return shape;
    }
}
