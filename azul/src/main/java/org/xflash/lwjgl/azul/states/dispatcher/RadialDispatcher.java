package org.xflash.lwjgl.azul.states.dispatcher;

import java.util.function.BiConsumer;

public class RadialDispatcher implements CoordDispatcher<Float> {
    private final int nb;
    private final int rayon;
    private final double offset;

    public RadialDispatcher(int nb, int rayon) {
        this.nb = nb;
        this.rayon = rayon;
        offset = 90;
    }

    public void dispatch(BiConsumer<Float, Float> consumer) {
        for (double angle = 0; angle < 360; angle += (360 / nb)) {
            double angle_rad = Math.toRadians(angle + offset);
            consumer.accept(
                    (float) Math.cos(angle_rad) * rayon,
                    (float) Math.sin(angle_rad) * rayon);
        }
    }
}
