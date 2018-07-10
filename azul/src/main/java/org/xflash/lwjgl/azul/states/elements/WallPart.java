package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.xflash.lwjgl.azul.states.dispatcher.CoordDispatcher;

import java.util.ArrayList;
import java.util.List;

public class WallPart {
    private final GameContainer container;
    private List<MouseOverArea> squares = new ArrayList<>();

    public WallPart(GameContainer container,
                    int x, int y,
                    int square,
                    CoordDispatcher<Float> coordDispatcher,
                    final ComponentListener componentListener) {
        this.container = container;
        coordDispatcher.dispatch((x1, y1) -> {
            squares.add(new MouseOverArea(container, null,
                    x + x1.intValue(), y + y1.intValue(),
                    square, square,
                    componentListener));
        });
    }


    public void render(GUIContext guiContext, Graphics g) throws SlickException {
//        g.translate(x, y);
        g.setColor(Color.white);
        for (MouseOverArea square : squares) {
            square.render(guiContext, g);
        }
//        g.resetTransform();
    }
}
