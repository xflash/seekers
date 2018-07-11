package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.model.Player;
import org.xflash.lwjgl.azul.model.Tile;
import org.xflash.lwjgl.azul.ui.MouseOverShape;

import java.util.ArrayList;
import java.util.List;

public class PlayerPicker {
    private static final int NB_MAX = 4;
    private final int margin = 5;
    private final float sq = 50;
    private final GUIContext guiContext;
    private final int x;
    private final int y;
    private List<Shape> shapes = new ArrayList<>();
    private List<MouseOverShape> toPlay = new ArrayList<>();

    public PlayerPicker(GUIContext guiContext, int x, int y) {
        this.guiContext = guiContext;
        this.x = x;
        this.y = y;
        for (int i = 0; i < NB_MAX; i++) {
            shapes.add(new Rectangle(x + i * (sq + margin), y, sq, sq));
        }
    }

    public void onPlayerChange(Player currentPlayer) {
        System.out.println("PlayerPicker onPlayerChange = " + currentPlayer);

    }

    public void onPlayerTilesChange(List<Tile> tiles) {
        System.out.println("PlayerPicker onPlayerTilesChange = " + tiles);
        toPlay.clear();
        int tilesNb = tiles.size();
        for (int i = 0; i < tilesNb; i++) {
            Tile tileToPlay = tiles.get(i);
            MouseOverShape mouseOverShape = new MouseOverShape(guiContext, x + i * (sq + margin), y) {
                @Override
                public Shape createShapeAt(int x, int y) {
                    float v = sq - 5f;
                    return new Rectangle(x + 2f, y + 2f, v, v);
                }
            };
            toPlay.add(mouseOverShape);
            Color color = tileToPlay.getColor();
            mouseOverShape.setColor(color);
            mouseOverShape.setOverColor(color.brighter());
            mouseOverShape.setDownColor(color.darker());

            mouseOverShape.addListener((source -> {
                System.out.println("player pick = " + source);
            }));
        }
    }

    public void render(GameContainer container, Graphics g) {
        for (int i = 0; i < NB_MAX; i++) {
            Shape shape = shapes.get(i);
            g.setColor(Color.lightGray);
            g.draw(shape);
        }
        for (MouseOverShape mouseOverShape : toPlay) {
            mouseOverShape.render(guiContext, g);
        }
    }
}
