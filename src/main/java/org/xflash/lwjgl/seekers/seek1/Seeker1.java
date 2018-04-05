package org.xflash.lwjgl.seekers.seek1;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;

public class Seeker1 extends BasicGame {

    private final int nb;
    private ArrayList<Boid> boids = new ArrayList<>();

    public Seeker1() {
        super("Seeker1");
        nb = 20;
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        for (int i = 0; i < nb; i++) {
            boids.add(new Boid(
                    (float)(container.getWidth() * Math.random()),
                    (float)(container.getHeight()* Math.random()),
                    5.f));
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        for (Boid boid : boids) {
            boid.update(container, delta, new Vector2f(input.getMouseX(), input.getMouseY()));
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        Input input = container.getInput();
        g.setColor(new Color(0x323232));
        g.drawOval(input.getMouseX(), input.getMouseY(), 10, 10);

        for (Boid boid : boids) {
            boid.render(container, g);
        }
    }
}
