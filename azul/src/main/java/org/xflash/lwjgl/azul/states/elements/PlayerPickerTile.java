package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.model.Tile;
import org.xflash.lwjgl.azul.ui.MouseOverShape;

public class PlayerPickerTile extends MouseOverShape{
    private final Tile tile;
    private final float sq;

    public PlayerPickerTile(Tile tile, GUIContext guiContext, int x, int y, int sq, final ComponentListener listener) {
        super(guiContext, x, y);
        this.tile = tile;
        this.sq = sq;
        setLocation(x, y);
        Color color = tile.getColor();
        setColor(color);
        setOverColor(color.darker(.2f));
        setDownColor(color.darker());

        addListener(listener);
    }

    public Tile getTile() {
        return tile;
    }

    @Override
    public Shape createShapeAt(int x1, int y1) {
        return new Rectangle(x1 + 2f, y1 + 2f, sq, sq);
    }

    public void renderSelection(Graphics g) {
        g.setColor(mouseDownColor);
        int i = getWidth() / 2;
        g.fill(new Circle(getX()+ i, getY()+i, i-2));
//        g.setColor(mouseDownColor);
//        g.draw(area);
//        updateImage();
    }
}
