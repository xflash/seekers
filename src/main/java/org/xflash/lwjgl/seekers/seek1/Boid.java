package org.xflash.lwjgl.seekers.seek1;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Boid {

    public static float MAX_FORCE = 10.4f;
    public static float MAX_VELOCITY = 10f;

    public Vector2f position;
    public Vector2f velocity;
    public float mass;
    private final float height = 20;
    private final float width = 20;


    public Boid(float posX, float posY) {
        this(posX, posY, 10);
    }

    public Boid(float posX, float posY, float totalMass) {
        position = new Vector2f(posX, posY);
        velocity = new Vector2f(-1, -2);
        mass = totalMass;

        truncate(velocity, MAX_VELOCITY);
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

    }

    public void render(GameContainer container, Graphics graphics) {

//        graphics.beginFill(0xFF0000);
        graphics.setColor(Color.red);
        graphics.drawRect(position.getX(), position.getY(), width, height);
//        graphics.endFill();

//        graphics.setColor(Color.green);
//        graphics.drawLine(position.x, position.y, velocity.x, velocity.y);

        renderForces(graphics, velocity, new Color(0x00FF00));

    }


    private void renderForces(Graphics graphics, Vector2f force, Color color){
        renderForces(graphics, force, color, 100.f);
    }
    private void renderForces(Graphics graphics, Vector2f force, Color color, float scale) {

        float lineWidth = graphics.getLineWidth();
        graphics.setLineWidth(2);
        graphics.setColor(color);
        graphics.drawLine(
                position.x + width / 2.f,
                position.y + height / 2.f,
                position.x + force.x * scale,
                position.y + force.y * scale);
        graphics.setLineWidth(lineWidth);
    }
}
