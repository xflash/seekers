package org.xflash.lwjgl.seekers.basic;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Boid {

    private static float MAX_FORCE = 2.4f;
    private static float MAX_VELOCITY = 3f;

    Vector2f position;
    Vector2f velocity;
    Vector2f desired;
    Vector2f steering;
    private float mass;
    Shape shape;


    public Boid(float posX, float posY) {
        this(posX, posY, 10);
    }

    public Boid(float posX, float posY, float totalMass) {
        position = new Vector2f(posX, posY);
        velocity = new Vector2f(-1, -2);
        desired = new Vector2f(0, 0);
        steering = new Vector2f(0, 0);

        mass = totalMass;

        truncate(velocity, MAX_VELOCITY);
        shape = createShape();
        shape = shape.transform(Transform.createRotateTransform(
                (float) Math.toRadians(90.)
        ));
    }

    private Polygon createShape() {
        Polygon polygon = new Polygon();
        polygon.addPoint(0, -20);
        polygon.addPoint(10, 20);
        polygon.addPoint(-10, 20);
        polygon.addPoint(0, -20);
        return polygon;
    }

    public void truncate(Vector2f vector, float max) {
        vector.scale(Math.min(1.f, max / vector.length()));
    }

    private Vector2f seek(Vector2f target) {
        desired = target.copy().sub(position);
        desired.normalise();
        desired.scale(MAX_VELOCITY);

        return desired.copy().sub(velocity);
    }

    public void update(GameContainer container, int delta, Vector2f mouse) {

        steering = seek(mouse);

        truncate(steering, MAX_FORCE);
        steering.scale(1 / mass);

        velocity.add(steering);
        truncate(velocity, MAX_VELOCITY);

        position.add(velocity);
    }

    public void render(GameContainer container, Graphics graphics) {

//        graphics.beginFill(0xFF0000);
        graphics.setColor(Color.red);
//        graphics.drawRect(position.getX(), position.getY(), width, height);

        graphics.translate(position.x, position.y);
        float cx = shape.getCenterX();
        float cy = shape.getCenterY();
        graphics.drawLine(cx - 2, cy, cx + 2, cy);
        graphics.drawLine(cx, cy - 2, cx, cy + 2);
        graphics.rotate(cx, cy,
                (float) velocity.getTheta()
        );
        graphics.draw(shape);
        graphics.resetTransform();

        graphics.drawString(String.format("vel: %f", velocity.getTheta()), position.x + 10, position.y);

//        graphics.endFill();

//        graphics.setColor(Color.green);
//        graphics.drawLine(position.x, position.y, velocity.x, velocity.y);

    }


}
