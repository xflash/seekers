package org.xflash.lwjgl.seekers.basic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

public interface ISteeringManager {

    // The public API (one method for each behavior)
    void seek(Vector2f target);
    void seek(Vector2f target, float slowingRadius);

    void flee(Vector2f target);

    void wander();

//    void evade(IBoid target);

    void pursuit(IBoid target);

    void update(GameContainer container);
}
