package org.xflash.lwjgl.seekers.seek2;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;

public class Seeker2 extends BasicGame {

    private final int nb;
    private ArrayList<Boid2> boids = new ArrayList<>();

    public Seeker2() {
        super("Seeker2");
        nb = 1;
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        for (int i = 0; i < nb; i++) {
            boids.add(new Boid2(
                    (float)(container.getWidth() * Math.random()),
                    (float)(container.getHeight()* Math.random()),
                    75.f));
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        if(Input.KEY_ESCAPE==key) System.exit(0);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        for (Boid2 boid : boids) {
            boid.update(container, delta, new Vector2f(input.getMouseX(), input.getMouseY()));
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        Input input = container.getInput();
        g.setColor(new Color(0x323232));
        g.fillOval(input.getMouseX()-5, input.getMouseY()-5, 10, 10);

        for (Boid2 boid : boids) {
            boid.render(container, g);
            boid.renderForces(g, boid.velocity, new Color(0x00FF00), 10);
            boid.renderForces(g, boid.desired, new Color(0x454545), 10);
            boid.renderForces(g, boid.steering, new Color(0x0000FF));



        }
    }
}
