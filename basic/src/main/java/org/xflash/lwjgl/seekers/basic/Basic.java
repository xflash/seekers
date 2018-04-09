package org.xflash.lwjgl.seekers.basic;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;

public class Basic extends BasicGame {

    private final int nb;
    private ArrayList<Boid> boids = new ArrayList<>();

    public Basic() {
        super("Basic");
        nb = 2;
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        for (int i = 0; i < nb; i++) {
            boids.add(new Boid(
                    (float) (container.getWidth() * Math.random()),
                    (float) (container.getHeight() * Math.random()),
                    ((float) (5.f + Math.random() * 20.))));
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

//        g.clear();
        g.setBackground(Color.white);

        for (Boid boid : boids) {
            boid.render(container, g);
            renderForces(g, boid);
        }

        // Target
        g.setColor(new Color(0x323232));
        g.drawOval(input.getMouseX(), input.getMouseY(), 10, 10);

    }

    private void renderForces(Graphics g, Boid boid) {

        Vector2f desired = boid.desired.copy();
        Vector2f velocity = boid.velocity.copy();
        Vector2f steering = boid.steering.copy();

        velocity.normalise();
        desired.normalise();
        steering.normalise();

        // Force vectors
        renderForces(g, boid, velocity, new Color(0x00FF00), 100.f);
        renderForces(g, boid, desired, new Color(0x454545), 100.f);
        renderForces(g, boid, steering, new Color(0x0000FF), 10.f);
    }


    private void renderForces(Graphics graphics, Boid boid, Vector2f force, Color color, float scale) {
        graphics.setLineWidth(2);
        graphics.setColor(color);
        graphics.translate(boid.position.x, boid.position.y);
        graphics.translate(boid.shape.getCenterX(), boid.shape.getCenterY());
        graphics.drawLine(0, 0, force.x * scale, force.y * scale);
        graphics.resetTransform();
        graphics.resetLineWidth();
    }
}
