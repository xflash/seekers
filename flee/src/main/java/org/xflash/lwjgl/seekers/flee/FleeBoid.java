package org.xflash.lwjgl.seekers.flee;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class FleeBoid {

    public static float MAX_FORCE = .6f;
    public static float MAX_VELOCITY = 3f;
    final float height = 20;
    final float width = 20;
    private final Color color;
    public Vector2f position;
    public Vector2f velocity;
    public Vector2f desired;
    public Vector2f steering;

    public float mass;


    public FleeBoid(float posX, float posY) {
        this(posX, posY, 20);
    }

    public FleeBoid(float posX, float posY, float totalMass) {
        position = new Vector2f(posX, posY);
        velocity = new Vector2f(-1, -2);
        desired	 = new Vector2f(0, 0);
        steering = new Vector2f(0, 0);
        mass = totalMass;

        truncate(velocity, MAX_VELOCITY);
        color = new Color(0x7e7f7f);
    }

    private void truncate(Vector2f vector, float max) {
        vector.scale(Math.min(max / vector.length(), 1.f));
    }


    public void update(GameContainer container, int delta, Vector2f mouse) {
//        steering = seek(mouse);
        steering = flee(mouse);

        truncate(steering, MAX_FORCE);
        steering.scale(1 / mass);

        velocity.add(steering);
        truncate(velocity, MAX_VELOCITY);

        position.add(velocity);


        if (position.x < 0 || position.x > container.getWidth()
                || position.y < 0 || position.y > container.getHeight()) {
            reset(container);
        }
    }

    private Vector2f flee(Vector2f target ) {
        desired = position.copy().sub(target);
        desired.normalise();
        desired.scale(MAX_VELOCITY);

        return desired.copy().sub(velocity);
    }

    private Vector2f seek(Vector2f target) {
        desired = target.copy().sub(position);
        desired.normalise();
        desired.scale(MAX_VELOCITY);

        return desired.copy().sub(velocity);

    }

    public void render(GameContainer container, Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(position.getX(), position.getY(), width, height);
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
}
