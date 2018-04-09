package org.xflash.lwjgl.seekers.basic;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Boid implements IBoid {

    private static float MAX_VELOCITY = 3f;

    Vector2f position;
    Vector2f velocity;
    private float mass;
    Shape shape;
    SteeringManager steeringManager;


    public Boid(float posX, float posY) {
        this(posX, posY, 10);
    }

    public Boid(float posX, float posY, float totalMass) {
        position = new Vector2f(posX, posY);
        velocity = new Vector2f(-1, -2);
        mass = totalMass;

        steeringManager = new SteeringManager(this);

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

    public void update(GameContainer container, int delta, Vector2f mouse) {

        steeringManager.flee(mouse);
        steeringManager.update(container);
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
        graphics.fill(shape);
        graphics.resetTransform();

//        graphics.drawString(String.format("vel: %f", velocity.getTheta()), position.x + 10, position.y);
    }


    @Override
    public Vector2f getPosition() {
        return position;
    }

    @Override
    public Vector2f getVelocity() {
        return velocity;
    }

    @Override
    public float getMaxVelocity() {
        return MAX_VELOCITY;
    }

    @Override
    public float getMass() {
        return mass;
    }
}
