package org.xflash.lwjgl.azul.states.dispatcher;

import java.util.function.BiConsumer;

public class RadialDispatcher implements CoordDispatcher<Float> {
    private final int nb;
    private final float radius;
    private final double offset;

    public RadialDispatcher(int nb, float radius) {
        this.nb = nb;
        this.radius = radius;
        offset = 90;
    }

    public void dispatch(BiConsumer<Float, Float> consumer) {
        for (double angle = 0; angle < 360; angle += (360 / nb)) {
            double angle_rad = Math.toRadians(angle + offset);
            consumer.accept(
                    (float) Math.cos(angle_rad) * radius,
                    (float) Math.sin(angle_rad) * radius);
        }
    }
}
