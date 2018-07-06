package org.xflash.lwjgl.azul.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public class Button extends MouseOverArea {

    private final String label;

    public Button(GUIContext container, String label, int x, int y, ComponentListener componentListener) {
        super(container, null, x, y, 200, 25, componentListener);
        this.label = label;

        setNormalColor(Color.lightGray);
        setMouseOverColor(Color.lightGray.brighter());
        setMouseDownColor(Color.darkGray);
    }


    @Override
    public void render(GUIContext container, Graphics g) {
        super.render(container, g);
        Color oldColor = g.getColor();
        Font font = g.getFont();

        int width = font.getWidth(label);

        g.setColor(Color.black);
        g.drawString(label,
                getX() + (getWidth() / 2) - width / 2,
                getY() + (getHeight() / 2) - font.getLineHeight() / 2);

        g.setColor(Color.white);
        g.drawLine(
                1 + getX(), getY() + getHeight() - 1,
                getX() + getWidth() - 1, getY() + getHeight() - 1);

        g.drawLine(getX() + getWidth() - 1, getY() + 1,
                getX() + getWidth() - 1, getY() + getHeight() - 1);

        g.setColor(oldColor);
    }

}
