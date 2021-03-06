package org.xflash.lwjgl.seekers.pursuit;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.xflash.lwjgl.seekers.pursuit.PursuitBoid;

import java.util.ArrayList;

public class PursuitGame extends BasicGame {

    private ArrayList<PursuitBoid> boids = new ArrayList<>();
    public boolean showForces 	= false;

    public PursuitGame() {
        super("PursuitGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        for (int i = 0; i < 20; i++) {
            PursuitBoid boid = new PursuitBoid(
                    ((float) (container.getWidth() /2)),
                    ((float) (container.getHeight() /2)),
                    ((float) (20. + Math.random() * 20.)),
                    null
            );
            boids.add(boid);
            boid.reset(container);

            boids.get(boids.size()- 1).target = null;

            for (i = 0; i < boids.size(); i++) {
                if(i != boids.size() - 1) {
                    boids.get(i).target = boids.get(boids.size() - 1);
                }
            }
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) System.exit(0);
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        showForces = !showForces;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        for (PursuitBoid boid : boids) {
            boid.update(container, delta, new Vector2f(input.getMouseX(), input.getMouseY()));
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {


        Input input = container.getInput();
//        g.setColor(new Color(0x323232));
//        g.fillOval(input.getMouseX() - 5, input.getMouseY() - 5, 10, 10);

        for (PursuitBoid boid : boids) {
            boid.render(container, g);
            if(showForces)
                drawForces(boid, g);
        }
    }

    private void drawForces(PursuitBoid boid, Graphics g) {

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


    void drawForceVector(Graphics graphics, PursuitBoid boid, Vector2f force, Color color, int scale) {
        graphics.setColor(color);
        graphics.setLineWidth(2);
        graphics.drawLine(
                boid.position.x + boid.shape.getWidth() / 2.f,
                boid.position.y + boid.shape.getHeight() / 2.f,
                boid.position.x + force.x * scale,
                boid.position.y + force.y * scale);
        graphics.resetLineWidth();
    }
}
