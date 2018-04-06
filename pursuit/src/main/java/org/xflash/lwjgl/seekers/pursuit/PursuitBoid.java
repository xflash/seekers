package org.xflash.lwjgl.seekers.pursuit;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class PursuitBoid {

    public static float MAX_FORCE = 5.4f;
    public static float MAX_VELOCITY = 6;

    public Vector2f position;
    public Vector2f velocity;
    public PursuitBoid target;
    public Vector2f desired;
    public Vector2f steering;
    public float mass;

    public Vector2f distance = new Vector2f();
    public Vector2f targetFuturePosition = new Vector2f();
    Shape shape;

    public PursuitBoid (float posX , float posY , float totalMass , PursuitBoid targetToPursuit ) {
        position 	= new Vector2f(posX, posY);
        velocity 	= new Vector2f(-1, -2);
        target	 	= targetToPursuit;
        desired	 	= new Vector2f(0, 0);
        steering 	= new Vector2f(0, 0);
        mass	 	= totalMass;

        truncate(velocity, MAX_VELOCITY);

        Polygon polygon = new Polygon();
        polygon.addPoint(0, -20);
        polygon.addPoint(10, 20);
        polygon.addPoint(-10, 20);
        polygon.addPoint(0, -20);
        shape = polygon;


    }

    private Vector2f seek(Vector2f target ) {
        desired = target.copy().sub(position);
        desired.normalise();
        desired.scale(MAX_VELOCITY);

        return desired.copy().sub(velocity);
    }

    private Vector2f flee(Vector2f target ) {

        desired = position.copy().sub(target);
        desired.normalise();
        desired.scale(MAX_VELOCITY);

        return desired.copy().sub(velocity);
    }

    private Vector2f pursuit(PursuitBoid target ) {
        Vector2f tv = target.velocity.copy();
        tv.scale(30);

        targetFuturePosition = target.position.copy().add(tv);

        return seek(targetFuturePosition);
    }

    public void truncate(Vector2f vector , float max) {
        vector.scale(Math.min(max / vector.length(), 1.f));
    }

    public float getAngle(Vector2f vector ){
        return (float) Math.atan2(vector.y, vector.x);
    }

    public void update(GameContainer container, int delta, Vector2f mouse) {
        steering = target == null ? seek(mouse) : pursuit(target);

        truncate(steering, MAX_FORCE);
        steering.scale(1 / mass);

        velocity = velocity.add(steering);
        truncate(velocity, MAX_VELOCITY);

        position = position.add(velocity);

        // Adjust boid rodation to match the velocity vector.
        shape= shape.transform(Transform.createRotateTransform(
//                (float) Math.toRadians(90 + (180 * getAngle(velocity)) / Math.PI)
                getAngle(velocity)
        ));

        if (position.x < 0 || position.x > container.getWidth()
                || position.y < 0 || position.y > container.getHeight()) {
            reset(container);
        }
    }

    public void reset(GameContainer container) {
        position.x = container.getWidth() / 2;
        position.y = container.getHeight() / 2;

        velocity.x = -1 * (Math.random() < 0.5 ? -2 : 1);
        velocity.y = -1 * (Math.random() < 0.5 ? -2 : 1);

        truncate(velocity, MAX_VELOCITY * 0.5f);
        desired.x = desired.y = 0;
        steering.x = steering.y = 0;
    }

    public void render(GameContainer container, Graphics g) {


        g.setLineWidth(2);
        g.setColor(new Color(0xffaabb));
        shape.setLocation(position);
        g.draw(shape);
    }
}
