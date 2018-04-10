package org.xflash.lwjgl.seekers.basic;

import org.newdawn.slick.*;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Boid implements IBoid {

    private static float MAX_VELOCITY = 3f;
    private final int id;

    Vector2f position;
    Vector2f velocity;
    private float mass;
    Shape shape;
    SteeringManager steeringManager;
    public Boid target;


    public Boid(int id, float posX, float posY, float totalMass) {
        this.id = id;
        position = new Vector2f(posX, posY);
        velocity = new Vector2f(-1, -2);
        mass = totalMass;

        steeringManager = new SteeringManager(this);

        shape = createShape();
//        shape = shape.transform(Transform.createRotateTransform((float) Math.toRadians(90.)));
    }

    private Polygon createShape() {
        Polygon polygon = new Polygon();
        polygon.addPoint(20, 0);
        polygon.addPoint(-20, 10);
        polygon.addPoint(-20, -10);
        polygon.addPoint(20, 0);
//        polygon.addPoint(0, -20);
//        polygon.addPoint(10, 20);
//        polygon.addPoint(-10, 20);
//        polygon.addPoint(0, -20);
        return polygon;
    }

    public void update(GameContainer container, int delta, Vector2f mouse) {

//        steeringManager.seek(mouse);
//        steeringManager.wander();

        if(target!=null)
            steeringManager.pursuit(target);
        else
            steeringManager.seek(mouse);

        steeringManager.update(container);
    }

    public void render(GameContainer container, Graphics graphics) {

        renderInCenter(graphics, this::renderShape);
//        renderInCenter(graphics, this::renderCenter);
        renderInCenter(graphics, this::renderId);
//        renderInCenter(graphics, (g, center)->{
//            renderShape(g, center);
//            renderCenter(g, center);
//            renderId(g, center);
//        });
    }

    private void renderId(Graphics graphics, Vector2f center) {
        graphics.rotate(center.x, center.y, 90);
        String s = String.format("%d", id);
        Font font = graphics.getFont();
        int width = font.getWidth(s);
        int height = font.getHeight(s);
        graphics.setColor(Color.blue);
        graphics.drawString(s, -1+center.x-width/2, center.y-height/2);
    }

    private void renderCenter(Graphics graphics, Vector2f center) {
        float cx = center.x;
        float cy = center.y;

        graphics.setColor(Color.yellow);
        graphics.drawLine(cx - 2, cy, cx + 2, cy);
        graphics.drawLine(cx, cy - 2, cx, cy + 2);
    }

    private void renderShape(Graphics graphics, Vector2f center) {
        graphics.setColor(Color.red);
        graphics.fill(shape);
    }

    void renderInCenter(Graphics graphics, BiConsumer<Graphics, Vector2f> consumer) {
        float cx = shape.getCenterX();
        float cy = shape.getCenterY();
        graphics.translate(position.x, position.y);
        graphics.rotate(cx, cy, (float) velocity.getTheta());
        consumer.accept(graphics, new Vector2f(cx, cy));
        graphics.resetTransform();
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
