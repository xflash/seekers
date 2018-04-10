package org.xflash.lwjgl.seekers.basic;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;

public class Basic extends BasicGame {

    private final int nb;
    private ArrayList<Boid> boids = new ArrayList<>();
    private boolean showForces;

    public Basic() {
        super("Basic");
        nb = 3;
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        for (int i = 0; i < nb; i++) {
            Boid boid = new Boid(i,
                    (float) (container.getWidth()/2 *  Math.random() * 0.8),
                    (float) (container.getHeight()/2 * Math.random() * 0.8),
                    ((float) (20.f + Math.random() * 20.)));
            boids.add(boid);
//            boid.steeringManager.reset(container);
        }

        Boid lastBoid = boids.get(boids.size() - 1);
        lastBoid.target = null;
        for (int i = 0; i < boids.size(); i++) {
            if(i != boids.size() - 1) {
                boids.get(i).target = lastBoid;
            }
        }
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        showForces=!showForces;
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
            if(showForces)renderForces(g, boid);
        }

        // Target
        g.setColor(new Color(0x323232));
        g.drawOval(input.getMouseX(), input.getMouseY(), 10, 10);

    }

    private void renderForces(Graphics g, Boid boid) {

        Vector2f desired = boid.steeringManager.desired.copy();
        Vector2f velocity = boid.velocity.copy();
        Vector2f steering = boid.steeringManager.steering.copy();

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
