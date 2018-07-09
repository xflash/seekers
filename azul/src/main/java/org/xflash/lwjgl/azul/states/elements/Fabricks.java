package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.states.dispatcher.RadialDispatcher;

import java.util.ArrayList;
import java.util.List;

public class Fabricks {
    private final List<Fabrick> fabricks;
    private int x;
    private int y;

    public Fabricks(GUIContext guiContext, int nb) {
//        super(guiContext);

        fabricks = new ArrayList<>();
        int radius = 60;
        new RadialDispatcher(nb, radius)
                .dispatch((x, y) -> fabricks.add(new Fabrick(guiContext, x, y, radius/2)));
    }

    public void render(GUIContext guiContext, Graphics g) throws SlickException {
        g.translate(x, y);
        for (Fabrick fabrick : fabricks) {
            g.draw(fabrick.getShape());
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
