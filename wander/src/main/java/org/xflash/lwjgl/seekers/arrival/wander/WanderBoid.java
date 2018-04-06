package org.xflash.lwjgl.seekers.arrival.wander;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class WanderBoid {

    public static float MAX_FORCE = 5.4f;
    public static float MAX_VELOCITY = 3f;
    public static float CIRCLE_DISTANCE= 6f;
    public static float CIRCLE_RADIUS = 8f;
    public static float ANGLE_CHANGE = 1f;

    final float height = 20;
    final float width = 20;
    private final Color color;
    private float rotation;
    private float wanderAngle;
    public Vector2f position;
    public Vector2f velocity;
    public Vector2f desired;
    public Vector2f steering;

    public float mass;
    public float nextDecision;


    public WanderBoid(float posX, float posY) {
        this(posX, posY, 20);
    }

    public WanderBoid(float posX, float posY, float totalMass) {
        position = new Vector2f(posX, posY);
        velocity = new Vector2f(-1, -2);
        desired = new Vector2f(0, 0);

        steering = new Vector2f(0, 0);
        mass = totalMass;
        nextDecision=0;

        truncate(velocity, MAX_VELOCITY);
        color = new Color(0x7e7f7f);
        wanderAngle=rotation = 0.f;
    }

    private Vector2f wander(GameContainer container){

        Vector2f circleCenter = velocity.copy();
        circleCenter.normalise();
        circleCenter.scale(CIRCLE_DISTANCE);

        Vector2f displacement = new Vector2f(0, -1);
        displacement.scale(CIRCLE_RADIUS);

        setAngle(displacement, wanderAngle);
        wanderAngle += Math.random() * ANGLE_CHANGE - ANGLE_CHANGE * .5;

        return circleCenter.copy().add(displacement);

    }

    private void truncate(Vector2f vector, float max) {
        vector.scale(Math.min(max / vector.length(), 1.f));
    }

    public void setAngle(Vector2f vector, float value) {
        float len = vector.length();
        vector.x = (float) (Math.cos(value) * len);
        vector.y = (float) (Math.sin(value) * len);
    }

    public float getAngle(Vector2f vector ) {
        return (float) StrictMath.atan2(vector.y, vector.x);
    }


    public void update(GameContainer container, int delta, Vector2f mouse) {
        steering = wander(container);

        truncate(steering, MAX_FORCE);
        steering.scale(1 / mass);

        velocity.add(steering);
        truncate(velocity, MAX_VELOCITY);

        position.add(velocity);

        // Adjust boid rodation to match the velocity vector.
        rotation = (float) (90 + (180 * getAngle(velocity)) / Math.PI);

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

    public void render(GameContainer container, Graphics graphics) {
        graphics.setColor(color);
        graphics.rotate(position.x, position.y, rotation);
        graphics.fillRect(position.getX(), position.getY(), width, height);
    }
}
