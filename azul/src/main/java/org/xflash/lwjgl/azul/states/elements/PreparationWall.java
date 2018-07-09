package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.xflash.lwjgl.azul.states.dispatcher.HalfGridDispatcher;

import java.util.ArrayList;
import java.util.List;

public class PreparationWall {
    private final GameContainer container;
    private List<MouseOverArea> squares;
//    private float x;
//    private float y;
    private final int square;

    public PreparationWall(GameContainer container) {
        this.container = container;
        square = 30;
    }

    public void setLocation(int x, int y) {
//        this.x = x;
//        this.y = y;
        squares = new ArrayList<>();
        new HalfGridDispatcher(5, 3+ square)
                .dispatch((x1, y1) -> {
                    squares.add(new MouseOverArea(container, null,
                            x+x1.intValue(), y+y1.intValue(),
                            square, square,
                            source -> System.out.println("source = " + source)));
                });
    }


    public void render(GUIContext guiContext, Graphics g) throws SlickException {
//        g.translate(x, y);
        for (MouseOverArea square : squares) {
            square.render(guiContext, g);
        }
//        g.resetTransform();
    }
}
