package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.states.dispatcher.RadialDispatcher;

import java.util.ArrayList;
import java.util.List;

public class Fabricks {
    private final List<Fabrick> fabricks = new ArrayList<>();
    private final int radius = 60;

    public Fabricks(GUIContext guiContext, int nb, int x, int y, ComponentListener componentListener) {
        new RadialDispatcher(nb, radius)
                .dispatch((x1, y1) -> fabricks.add(new Fabrick(guiContext, x+x1, y+y1, radius /2, componentListener)));
    }

    public void render(GUIContext guiContext, Graphics g) throws SlickException {
//        g.translate(x, y);
        for (Fabrick fabrick : fabricks) {
            fabrick.render(guiContext, g);
        }
//        g.resetTransform();
    }
}
