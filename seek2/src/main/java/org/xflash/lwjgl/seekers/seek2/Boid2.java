package org.xflash.lwjgl.seekers.seek2;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Boid2 {

    public static float MAX_FORCE = 10.4f;
    public static float MAX_VELOCITY = 10f;
    private final float height = 20;
    private final float width = 20;
    public Vector2f position;
    public Vector2f velocity;
    public Vector2f desired;
    public Vector2f steering;

    public float mass;


    public Boid2(float posX, float posY) {
        this(posX, posY, 20);
    }

    public Boid2(float posX, float posY, float totalMass) {
        position = new Vector2f(posX, posY);
        velocity = new Vector2f(-1, -2);
        desired	 = new Vector2f(0, 0);
        steering = new Vector2f(0, 0);
        mass = totalMass;

        truncate(velocity, MAX_VELOCITY);
    }

    public void truncate(Vector2f vector, float max) {

        float i = max / vector.length();
        i = i < 1.0f ? i : 1.0f;

        vector.scale(i);
    }

    public void update(GameContainer container, int delta, Vector2f mouse) {

        steering = seek(mouse);

        truncate(steering, MAX_FORCE);
        steering.scale(1 / mass);

        velocity.add(steering);
        truncate(velocity, MAX_VELOCITY);

        position.add(velocity);


    }

    private Vector2f seek(Vector2f target) {
        desired = target.copy().sub(position);
        desired.normalise();
        desired.scale(MAX_VELOCITY);

        return desired.copy().sub(velocity);

    }

    public void render(GameContainer container, Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillRect(position.getX(), position.getY(), width, height);
    }


    void renderForces(Graphics graphics, Vector2f force, Color color) {
        renderForces(graphics, force, color, 100.f);
    }

    void renderForces(Graphics graphics, Vector2f force, Color color, float scale) {
        graphics.setColor(color);
        graphics.setLineWidth(2);
        graphics.drawLine(
                position.x + width / 2.f,
                position.y + height / 2.f,
                position.x + force.x * scale,
                position.y + force.y * scale);
        graphics.resetLineWidth();
    }
}
