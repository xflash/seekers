package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.xflash.lwjgl.azul.model.Tile;
import org.xflash.lwjgl.azul.model.TilesContainer;
import org.xflash.lwjgl.azul.states.dispatcher.CoordDispatcher;
import org.xflash.lwjgl.azul.ui.MouseOverShape;

import java.util.ArrayList;
import java.util.List;

public class WallPartPicker {
    private final GameContainer container;
    private final int x;
    private final int y;
    private final int square;
    private final CoordDispatcher<Float> coordDispatcher;
    private List<MouseOverShape> squares = new ArrayList<>();

    public WallPartPicker(GameContainer container,
                          TilesContainer tilesContainer,
                          int x, int y,
                          int square,
                          CoordDispatcher<Float> coordDispatcher
    ) {
        this.container = container;
        this.x = x;
        this.y = y;
        this.square = square;
        this.coordDispatcher = coordDispatcher;
        tilesContainer.addTilesObserver(evt -> {
            dispatch((List<Tile>)evt.getNewValue());
        });
    }

    public void dispatch(List<Tile> newValue){
        System.out.println("dispatch Tile = " + newValue);
        coordDispatcher.dispatch((x1, y1) -> {
            MouseOverShape moa = new MouseOverShape(container, x, y) {
                @Override
                public Shape createShapeAt(int x, int y) {
                    return new Rectangle(
                            x + x1.intValue(), y + y1.intValue(),
                            square, square);
                }
            };
            squares.add(moa);
            moa.setOverColor(Color.lightGray);
            moa.setDownColor(Color.darkGray);
            moa.addListener(evet->{
                System.out.println("evet = " + evet);
            });
        });
    }


    public void render(GUIContext guiContext, Graphics g) throws SlickException {
        g.setColor(Color.white);
        for (MouseOverShape mos : squares) {
            mos.render(guiContext, g);
        }
    }
}
