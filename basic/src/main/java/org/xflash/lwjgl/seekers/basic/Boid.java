package org.xflash.lwjgl.seekers.basic;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Boid {

    public static float MAX_FORCE = 10.4f;
    public static float MAX_VELOCITY = 10f;

    public Vector2f position;
    public Vector2f velocity;
    public float mass;
    private final float height = 20;
    private final float width = 20;
    private Shape shape;


    public Boid(float posX, float posY) {
        this(posX, posY, 10);
    }

    public Boid(float posX, float posY, float totalMass) {
        position = new Vector2f(posX, posY);
        velocity = new Vector2f(-1, -2);
        mass = totalMass;

        truncate(velocity, MAX_VELOCITY);
        shape = createShape();
        shape=shape.transform(Transform.createRotateTransform(
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

        float i = max / vector.length();
        i = i < 1.0f ? i : 1.0f;

        vector.scale(i);
    }

    public void update(GameContainer container, int delta, Vector2f mouse) {

        Vector2f target = mouse.copy();
        velocity = target.sub(position);
        velocity.normalise();
        velocity.scale(MAX_VELOCITY);
        velocity.scale(1 / mass);

        truncate(velocity, MAX_VELOCITY);
        position = position.add(velocity);

//        shape.setLocation(position);

    }

    public void render(GameContainer container, Graphics graphics) {

//        graphics.beginFill(0xFF0000);
        graphics.setColor(Color.red);
//        graphics.drawRect(position.getX(), position.getY(), width, height);

        graphics.translate(position.x, position.y);
        float cx = shape.getCenterX();
        float cy = shape.getCenterY();
        graphics.drawLine(cx-2, cy, cx+2, cy);
        graphics.drawLine(cx, cy-2, cx, cy+2);
        graphics.rotate(cx, cy,
                (float)velocity.getTheta()
        );
        graphics.draw(shape);
        graphics.resetTransform();

        graphics.drawString(String.format("vel: %f", velocity.getTheta()), position.x +10, position.y);

//        graphics.endFill();

//        graphics.setColor(Color.green);
//        graphics.drawLine(position.x, position.y, velocity.x, velocity.y);

    }


    void renderForces(Graphics graphics, Vector2f force, Color color){
        renderForces(graphics, force, color, 100.f);
    }
    private void renderForces(Graphics graphics, Vector2f force, Color color, float scale) {

        graphics.setLineWidth(2);
        graphics.setColor(color);

        graphics.translate(position.x, position.y);
        graphics.translate(shape.getCenterX(), shape.getCenterY());
//        graphics.drawLine(0,0, 10 , 10);
        graphics.drawLine(0,0, force.x * scale, force.y * scale);

//        graphics.drawLine(
//                position.x + width / 2.f,
//                position.y + height / 2.f,
//                position.x + force.x * scale,
//                position.y + force.y * scale);
        graphics.resetTransform();
        graphics.resetLineWidth();
    }
}
