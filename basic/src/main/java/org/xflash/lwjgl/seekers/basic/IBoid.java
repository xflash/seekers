package org.xflash.lwjgl.seekers.basic;

import org.newdawn.slick.geom.Vector2f;

public interface IBoid {
    Vector2f getPosition();

    Vector2f getVelocity();

    float getMaxVelocity();

    float getMass();
}
