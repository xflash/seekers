package org.xflash.lwjgl.seekers.flee;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;

public class FleeGame extends BasicGame {

    private ArrayList<FleeBoid> boids = new ArrayList<>();

    public FleeGame() {
        super("FleeGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        for (int i = 0; i < 6; i++) {
            FleeBoid boid = new FleeBoid(
                    ((float) (container.getWidth() / 2)),
                    ((float) (container.getHeight() / 2)),
                    ((float) (20. + Math.random() * 20.))
            );
            boids.add(boid);
            boid.reset(container);
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) System.exit(0);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        for (FleeBoid boid : boids) {
            boid.update(container, delta, new Vector2f(input.getMouseX(), input.getMouseY()));
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        Input input = container.getInput();
        g.setColor(new Color(0x323232));
        g.fillOval(input.getMouseX() - 5, input.getMouseY() - 5, 10, 10);

        for (FleeBoid boid : boids) {
            boid.render(container, g);
            drawForces(boid, g);

        }
    }

    private void drawForces(FleeBoid boid, Graphics g) {

        Vector2f desired = boid.desired.copy();
        Vector2f velocity = boid.velocity.copy();
        Vector2f steering = boid.steering.copy();

        velocity.normalise();
        desired.normalise();
        steering.normalise();


        drawForceVector(g, boid, boid.velocity, new Color(0x00FF00), 100);
        drawForceVector(g, boid, boid.desired, new Color(0x454545), 100);
        drawForceVector(g, boid, boid.steering, new Color(0xFF0000), 100);
    }


    void drawForceVector(Graphics graphics, FleeBoid boid, Vector2f force, Color color, int scale) {
        graphics.setColor(color);
        graphics.setLineWidth(2);
        graphics.drawLine(
                boid.position.x + boid.width / 2.f,
                boid.position.y + boid.height / 2.f,
                boid.position.x + force.x * scale,
                boid.position.y + force.y * scale);
        graphics.resetLineWidth();
    }
}
