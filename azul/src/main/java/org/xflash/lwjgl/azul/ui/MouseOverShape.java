package org.xflash.lwjgl.azul.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;


public abstract class MouseOverShape extends AbstractComponent {

    State state;
    boolean mouseUp = false;
    private Shape area;
    private boolean over;
    private boolean mouseDown;
    private Color currentColor;
    private Color normalColor = Color.white;
    protected Color mouseDownColor = Color.darkGray;
    private Color mouseOverColor = Color.lightGray;

    public MouseOverShape(GUIContext guiContext, float x, float y) {
        super(guiContext);
        setLocation((int) x, (int) y);
        guiContext.getInput().addMouseListener(this);
    }

    @Override
    public void setLocation(int x, int y) {
        area = createShapeAt(x, y);
    }

    public abstract Shape createShapeAt(int x, int y);

    @Override
    public int getX() {
        return (int) area.getX();
    }

    @Override
    public int getY() {
        return (int) area.getY();
    }

    @Override
    public int getWidth() {
        return (int) area.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) area.getHeight();
    }

    public void render(GUIContext guiContext, Graphics g) {
        g.setColor(currentColor);
        g.fill(area);
        g.setColor(mouseDownColor);
        g.draw(area);
        updateImage();
    }

    private void updateImage() {

        if (!over) {
//            currentImage = normalImage;
            currentColor = normalColor;
            state = State.NORMAL;
            mouseUp = false;
        } else {
            if (mouseDown) {
                if ((state != State.MOUSE_DOWN) && (mouseUp)) {
                    currentColor = mouseDownColor;
                    state = State.MOUSE_DOWN;

                    notifyListeners();
                    mouseUp = false;
                }

                return;
            } else {
                mouseUp = true;
                if (state != State.MOUSE_OVER) {
                    currentColor = mouseOverColor;
                    state = State.MOUSE_OVER;
                }
            }
        }

    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#mouseMoved(int, int, int, int)
     */
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        over = area.contains(newx, newy);
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#mouseDragged(int, int, int, int)
     */
    @Override

    public void mouseDragged(int oldx, int oldy, int newx, int newy) {
        mouseMoved(oldx, oldy, newx, newy);
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#mousePressed(int, int, int)
     */
    @Override
    public void mousePressed(int button, int mx, int my) {
        over = area.contains(mx, my);
        if (button == 0) {
            mouseDown = true;
        }
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#mouseReleased(int, int, int)
     */
    @Override
    public void mouseReleased(int button, int mx, int my) {
        over = area.contains(mx, my);
        if (button == 0) {
            mouseDown = false;
        }
    }

    public void setColor(Color color) {
        normalColor =color;
    }

    public Color getColor() {
        return normalColor;
    }

    public void setOverColor(Color color) {
        mouseOverColor = color;
    }

    public void setDownColor(Color color) {
        mouseDownColor=color;
    }

    private enum State {
        NORMAL, MOUSE_DOWN, MOUSE_OVER
    }
}
