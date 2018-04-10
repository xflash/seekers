package org.xflash.lwjgl.seekers.basic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

public class SteeringManager implements ISteeringManager {
    public static final float CIRCLE_DISTANCE = 6;
    public static final float CIRCLE_RADIUS = 8;
    public static final float ANGLE_CHANGE = 1;
    private static final float DEFAULT_SLOWING_RADIUS = 20.f;
    private static float MAX_FORCE = 2.4f;
    public Vector2f steering;
    public IBoid host;
    public float wanderAngle;
    Vector2f desired;

    // The constructor
    public SteeringManager(IBoid host) {
        this.host = host;
        this.steering = new Vector2f(0, 0);
        this.desired=new Vector2f(0,0);

        wanderAngle = 0;
    }

    @Override
    public void seek(Vector2f target) {
        seek(target, DEFAULT_SLOWING_RADIUS);
    }

    /**
     * Receives a target to seek and a slowingRadius (used to perform arrive).
     *
     * @param target
     * @param slowingRadius
     */
    public void seek(Vector2f target, float slowingRadius) {
        steering.add(doSeek(target, slowingRadius));
    }

    @Override
    public void flee(Vector2f target) {
        steering.add(doFlee(target));
    }

    @Override
    public void wander() {
        steering.add(doWander());
    }

    private Vector2f doWander() {
//        var wanderForce :Vector3D, circleCenter:Vector3D, displacement:Vector3D;

        Vector2f wanderForce = host.getVelocity().copy();
        wanderForce.normalise();
        wanderForce.scale(CIRCLE_DISTANCE);

        Vector2f displacement = new Vector2f(0, -1);
        displacement.scale(CIRCLE_RADIUS);
        displacement.setTheta(Math.toDegrees(wanderAngle));
        wanderAngle += Math.random() * ANGLE_CHANGE - ANGLE_CHANGE * .5;

        return wanderForce.add(displacement);
    }


    public float getAngle(Vector2f vector) {
        return (float) Math.atan2(vector.y, vector.x);
    }

    @Override
    public void pursuit(IBoid target) {
        steering.add(doPursuit(target));
    }

    @Override
    public void evade(IBoid target) {
        steering.add(doEvade(target));
    }

    @Override
    public void update(GameContainer container) {
        Vector2f velocity = host.getVelocity();
        Vector2f position = host.getPosition();

        truncate(steering, MAX_FORCE);
        steering.scale(1 / host.getMass());

        velocity.add(steering);
        truncate(velocity, host.getMaxVelocity());

        position.add(velocity);



        if (position.x < 0 || position.x > container.getWidth()
                || position.y < 0 || position.y > container.getHeight()) {
            reset(container);
        }
    }

    void reset(GameContainer container) {
            Vector2f velocity = host.getVelocity();
            Vector2f position = host.getPosition();

            position.x = container.getWidth() / 2;
            position.y = container.getHeight() / 2;

            velocity.x = -1 * (Math.random() < 0.5 ? -2 : 1);
            velocity.y = -1 * (Math.random() < 0.5 ? -2 : 1);

            truncate(velocity, host.getMaxVelocity()* 0.5f);
            desired.x = desired.y = 0;
            steering.x = steering.y = 0;
    }

    public void truncate(Vector2f vector, float max) {
        vector.scale(Math.min(1.f, max / vector.length()));
    }



    /**
     * The real implementation of seek (with arrival code included)
     *
     * @param target
     * @return
     */
    private Vector2f doSeek(Vector2f target, float slowingRadius) {

        desired = target.copy().sub(host.getPosition());
        float distance = desired.length();
        desired.normalise();
        if (distance <= slowingRadius) {
            desired.scale(host.getMaxVelocity() * distance / slowingRadius);
        } else {
            desired.scale(host.getMaxVelocity());
        }

        return desired.copy().sub(host.getVelocity());

    }

    /**
     * @param target
     * @return
     */
    private Vector2f doFlee(Vector2f target) {
        desired = host.getPosition().copy().sub(target);
        desired.normalise();
        desired.scale(host.getMaxVelocity());
        return desired.copy().sub(host.getVelocity());
    }

    /**
     * @param target
     * @return
     */
    private Vector2f doPursuit(IBoid target) {
        Vector2f distance = target.getPosition().copy().sub(host.getPosition());

        float updatesNeeded = distance.length() / host.getMaxVelocity();

        Vector2f tv = target.getVelocity().copy();
        tv.scale(updatesNeeded);

        return doSeek(target.getPosition().copy().add(tv), 0);
    }

    /**
     * @param target
     * @return
     */
    private Vector2f doEvade(IBoid target) {
        Vector2f distance = target.getPosition().copy().sub(host.getPosition());

        float updatesAhead = distance.length() / host.getMaxVelocity();


        Vector2f tv = target.getPosition().copy().add(target.getVelocity());
        tv.scale(updatesAhead);

        return doFlee(tv);
    }


}